package com.backend.systemdesign.ai.service.ai.implementation;

import java.time.LocalDateTime;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.backend.systemdesign.ai.dto.ai.AIEvaluationResult;
import com.backend.systemdesign.ai.mapper.AIEvaluationMapper;
import com.backend.systemdesign.ai.model.Answer;
import com.backend.systemdesign.ai.model.Evaluation;
import com.backend.systemdesign.ai.model.QuestionType;
import com.backend.systemdesign.ai.prompt.DesignPromptBuilder;
import com.backend.systemdesign.ai.prompt.TheoryPromptBuilder;
import com.backend.systemdesign.ai.service.ai.EvaluationEngine;
import com.fasterxml.jackson.databind.ObjectMapper;

@Primary
@Service
public class OpenAIEvaluationEngine implements EvaluationEngine {

    private final ChatClient chatClient;

    private final ObjectMapper objectMapper;

    private final TheoryPromptBuilder theoryPrompt;

    private final DesignPromptBuilder designPrompt;

    private final AIEvaluationMapper aiEvaluationMapper;

    public OpenAIEvaluationEngine(ChatClient.Builder builder, ObjectMapper objectMapper,
            TheoryPromptBuilder theoryPrompt, DesignPromptBuilder designPrompt, AIEvaluationMapper aiEvaluationMapper) {
        this.chatClient = builder.build();
        this.objectMapper = objectMapper;
        this.theoryPrompt = theoryPrompt;
        this.designPrompt = designPrompt;
        this.aiEvaluationMapper = aiEvaluationMapper;
    }

    @Override
    public void evaluate(Answer answer, Evaluation evaluation) {

        String prompt = answer.getQuestion().getType() == QuestionType.DESIGN ? designPrompt.build(answer)
                : theoryPrompt.build(answer);

        String rawResponse = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        try {
            // Strip markdown code fences if AI adds them anyway
            String json = rawResponse.trim();
            json = json
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();

            AIEvaluationResult result = objectMapper.readValue(json, AIEvaluationResult.class);

            aiEvaluationMapper.mapToEvaluation(result, evaluation, answer);

        } catch (Exception e) {
            // Fallback if parsing fails — don't crash, store raw text
            evaluation.setOverallScore(0);
            evaluation.setOverallFeedback("AI evaluation failed to parse. Raw response: " + rawResponse);
        }

        evaluation.setEvaluatedAt(LocalDateTime.now());
        evaluation.setAnswer(answer);

    }

}
