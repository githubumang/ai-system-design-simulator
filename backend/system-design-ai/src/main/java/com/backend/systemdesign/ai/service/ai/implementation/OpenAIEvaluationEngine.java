package com.backend.systemdesign.ai.service.ai.implementation;

import java.time.LocalDateTime;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.backend.systemdesign.ai.model.Answer;
import com.backend.systemdesign.ai.model.Evaluation;
import com.backend.systemdesign.ai.model.QuestionType;
import com.backend.systemdesign.ai.service.ai.EvaluationEngine;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Primary
@Service
public class OpenAIEvaluationEngine implements EvaluationEngine {

    private final ChatClient chatClient;

    private final ObjectMapper objectMapper;

    public OpenAIEvaluationEngine(ChatClient.Builder builder, ObjectMapper objectMapper) {
        this.chatClient = builder.build();
        this.objectMapper = objectMapper;
    }

    @Override
    public void evaluate(Answer answer, Evaluation evaluation) {

        String rawResponse = chatClient.prompt()
                .user(buildPrompt(answer))
                .call()
                .content();

        try {
            // Strip markdown code fences if AI adds them anyway
            String json = rawResponse.trim();
            if (json.startsWith("```")) {
                json = json.replaceAll("```json", "").replaceAll("```", "").trim();
            }

            JsonNode node = objectMapper.readTree(json);

            evaluation.setOverallScore(node.get("overallScore").asInt());
            evaluation.setOverallFeedback(node.get("overallFeedback").asText());

            if (answer.getQuestion().getType() == QuestionType.DESIGN) {
                evaluation.setFunctionalRequirementsScore(node.get("functionalRequirementsScore").asInt());
                evaluation.setNonFunctionalRequirementsScore(node.get("nonFunctionalRequirementsScore").asInt());
                evaluation.setApiDesignScore(node.get("apiDesignScore").asInt());
                evaluation.setDatabaseDesignScore(node.get("databaseDesignScore").asInt());
                evaluation.setScalingScore(node.get("scalingScore").asInt());
                evaluation.setTradeOffsScore(node.get("tradeOffsScore").asInt());
            } else {
                evaluation.setApiDesignScore(null);
                evaluation.setDatabaseDesignScore(null);
                evaluation.setFunctionalRequirementsScore(null);
                evaluation.setNonFunctionalRequirementsScore(null);
                evaluation.setScalingScore(null);
                evaluation.setTradeOffsScore(null);
            }

        } catch (Exception e) {
            // Fallback if parsing fails — don't crash, store raw text
            evaluation.setOverallScore(0);
            evaluation.setOverallFeedback("AI evaluation failed to parse. Raw response: " + rawResponse);
        }

        evaluation.setEvaluatedAt(LocalDateTime.now());
        evaluation.setAnswer(answer);

    }

    private String buildPrompt(Answer answer) {

        if (answer.getQuestion().getType() == QuestionType.DESIGN) {
            return """
                    You are a senior system design interviewer. Evaluate the following system design answer.

                    Question: %s

                    Candidate's Answer:
                    - Functional Requirements: %s
                    - Non-Functional Requirements: %s
                    - API Design: %s
                    - Database Design: %s
                    - Scaling Strategy: %s
                    - Trade-offs: %s

                    Return ONLY a valid JSON object, no explanation, no markdown, no extra text:
                    {
                      "overallScore": <1-100>,
                      "functionalRequirementsScore": <1-100>,
                      "nonFunctionalRequirementsScore": <1-100>,
                      "apiDesignScore": <1-100>,
                      "databaseDesignScore": <1-100>,
                      "scalingScore": <1-100>,
                      "tradeOffsScore": <1-100>,
                      "overallFeedback": "<strengths, weaknesses, suggestions — concise, second person>"
                    }
                    """
                    .formatted(
                            answer.getQuestion().getTitle(),
                            answer.getFunctionalRequirements(),
                            answer.getNonFunctionalRequirements(),
                            answer.getApiDesign(),
                            answer.getDatabaseDesign(),
                            answer.getScalingStrategy(),
                            answer.getTradeOffs());
        } else {
            return """
                    You are a senior backend/system design interviewer. Evaluate the following theory answer.

                    Question: %s

                    Candidate's Answer: %s

                    Return ONLY a valid JSON object, no explanation, no markdown, no extra text:
                    {
                      "overallScore": <1-100>,
                      "overallFeedback": "<strengths, weaknesses, suggestions — concise, second person>"
                    }
                    """
                    .formatted(
                            answer.getQuestion().getTitle(),
                            answer.getUserAnswer());
        }
    }

}
