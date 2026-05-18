package com.backend.systemdesign.ai.dto.response;

import java.time.LocalDateTime;

import com.backend.systemdesign.ai.model.QuestionType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvaluationResponse {
    private Long answerId;

    private Integer overallScore;

    private Integer functionalRequirementsScore;

    private Integer nonFunctionalRequirementsScore;

    private Integer apiDesignScore;

    private Integer databaseDesignScore;

    private Integer scalingScore;

    private Integer tradeOffsScore;

    private String overallFeedback;

    private LocalDateTime evaluatedAt;

    private QuestionType questionType;
}
