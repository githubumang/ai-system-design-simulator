package com.backend.systemdesign.ai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.systemdesign.ai.model.Answer;
import com.backend.systemdesign.ai.model.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long>{
    Optional<Evaluation> findByAnswer(Answer answer);
}
