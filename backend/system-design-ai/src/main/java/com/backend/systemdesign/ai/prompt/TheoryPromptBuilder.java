package com.backend.systemdesign.ai.prompt;

import com.backend.systemdesign.ai.model.Answer;
import org.springframework.stereotype.Component;

@Component
public class TheoryPromptBuilder {

    public String build(Answer answer) {
        return """
                You are a senior backend/system design interviewer. Evaluate the following theory answer.

                Question: %s

                Candidate's Answer: %s

                Return ONLY a valid JSON object, no explanation, no markdown, no extra text, concise, second person:
                {
                  "overallScore": <1-10>,
                  "strengths":"...",
                  "weaknesses":"...",
                  "suggestions":"...",
                  "overallFeedback": "..."
                }
                """
                .formatted(
                        answer.getQuestion().getTitle(),
                        answer.getUserAnswer());
    }
}