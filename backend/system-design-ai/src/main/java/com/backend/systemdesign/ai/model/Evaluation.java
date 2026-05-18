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

    private Integer overallScore;

    private Integer functionalRequirementsScore;

    private Integer nonFunctionalRequirementsScore;

    private Integer apiDesignScore;

    private Integer databaseDesignScore;

    private Integer scalingScore;

    private Integer tradeOffsScore;

    @Column(columnDefinition = "TEXT")
    private String overallFeedback;

    private LocalDateTime evaluatedAt;
}
