package com.backend.systemdesign.ai.dto.response;

import java.time.LocalDateTime;

import com.backend.systemdesign.ai.model.EvaluationStatus;
import com.backend.systemdesign.ai.model.QuestionType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvaluationResponse {
    private Long answerId;

    private EvaluationStatus status;

    // Section-wise (DESIGN only)
    private Integer functionalRequirementsScore;
    private String functionalRequirementsFeedback;

    private Integer nonFunctionalRequirementsScore;
    private String nonFunctionalRequirementsFeedback;

    private Integer apiDesignScore;
    private String apiDesignFeedback;

    private Integer databaseDesignScore;
    private String databaseDesignFeedback;

    private Integer scalingScore;
    private String scalingFeedback;

    private Integer tradeOffsScore;
    private String tradeOffsFeedback;

    // Common
    private Integer overallScore;
    private String strengths;
    private String weaknesses;
    private String suggestions;
    private String overallFeedback;

    private LocalDateTime evaluatedAt;

    private QuestionType questionType;
}
