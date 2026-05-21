package com.backend.systemdesign.ai.prompt;

import com.backend.systemdesign.ai.model.Answer;
import org.springframework.stereotype.Component;

@Component
public class DesignPromptBuilder {

  public String build(Answer answer) {
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

        Return ONLY a valid JSON object, no explanation, no markdown, no extra text, concise- second person:
        {
          "overallScore": <1-10>,
          "strengths": "...",
          "weaknesses": "...",
          "suggestions": "...",
          "overallFeedback": "...",
          "functionalRequirements": { "score": <1-10>, "feedback": "..." },
          "nonFunctionalRequirements": { "score": <1-10>, "feedback": "..." },
          "apiDesign": { "score": <1-10>, "feedback": "..." },
          "databaseDesign": { "score": <1-10>, "feedback": "..." },
          "scaling": { "score": <1-10>, "feedback": "..." },
          "tradeOffs": { "score": <1-10>, "feedback": "..." }
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
  }
}