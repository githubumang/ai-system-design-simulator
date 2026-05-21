package com.backend.systemdesign.ai.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

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

    @Column(columnDefinition = "TEXT")
    private String overallFeedback;

    private Integer overallScore;
    private String strengths;
    private String weaknesses;
    private String suggestions;

    private LocalDateTime evaluatedAt;
}
