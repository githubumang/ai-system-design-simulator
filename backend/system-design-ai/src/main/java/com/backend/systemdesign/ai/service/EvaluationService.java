package com.backend.systemdesign.ai.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backend.systemdesign.ai.dto.response.EvaluationResponse;
import com.backend.systemdesign.ai.exception.ResourceNotFoundException;
import com.backend.systemdesign.ai.mapper.EvaluationMapper;
import com.backend.systemdesign.ai.model.Answer;
import com.backend.systemdesign.ai.model.Evaluation;
import com.backend.systemdesign.ai.repository.AnswerRepository;
import com.backend.systemdesign.ai.repository.EvaluationRepository;
import com.backend.systemdesign.ai.service.ai.EvaluationEngine;

@Service
public class EvaluationService {

    private final AnswerRepository answerRepository;

    private final EvaluationRepository evaluationRepository;

    private final EvaluationEngine evaluationEngine;

    private final EvaluationMapper evaluationMapper;

    public EvaluationService(AnswerRepository answerRepository, EvaluationRepository evaluationRepository,
            EvaluationEngine evaluationEngine, EvaluationMapper evaluationMapper) {
        this.answerRepository = answerRepository;
        this.evaluationRepository = evaluationRepository;
        this.evaluationEngine = evaluationEngine;
        this.evaluationMapper = evaluationMapper;
    }

    public EvaluationResponse generateEvaluation(long answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found"));

        Evaluation evaluation;
        Optional<Evaluation> existing = evaluationRepository.findByAnswer(answer);

        if (existing.isPresent()) {
            evaluation = existing.get();
        } else {
            evaluation = new Evaluation();
        }

        evaluationEngine.evaluate(answer, evaluation);

        Evaluation savedEvaluation = evaluationRepository.save(evaluation);

        return evaluationMapper.toResponse(savedEvaluation);
    }
}
