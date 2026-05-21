package com.backend.systemdesign.ai.dto.ai;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AIEvaluationResult {
    private Integer overallScore;
    private String strengths;
    private String weaknesses;
    private String suggestions;
    private String overallFeedback;

    // Only for DESIGN questions
    private DesignSectionResult functionalRequirements;
    private DesignSectionResult nonFunctionalRequirements;
    private DesignSectionResult apiDesign;
    private DesignSectionResult databaseDesign;
    private DesignSectionResult scaling;
    private DesignSectionResult tradeOffs;
}