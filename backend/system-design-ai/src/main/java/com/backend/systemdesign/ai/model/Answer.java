package com.backend.systemdesign.ai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "user_id", "question_id" })
})
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(columnDefinition = "TEXT")
    private String userAnswer;

    @Column(columnDefinition = "TEXT")
    private String functionalRequirements;

    @Column(columnDefinition = "TEXT")
    private String nonFunctionalRequirements;

    @Column(columnDefinition = "TEXT")
    private String apiDesign;

    @Column(columnDefinition = "TEXT")
    private String databaseDesign;

    @Column(columnDefinition = "TEXT")
    private String scalingStrategy;

    @Column(columnDefinition = "TEXT")
    private String tradeOffs;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}