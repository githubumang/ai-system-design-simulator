package com.backend.systemdesign.ai.service;

import org.springframework.stereotype.Service;

import com.backend.systemdesign.ai.dto.response.EvaluationResponse;
import com.backend.systemdesign.ai.exception.ResourceNotFoundException;
import com.backend.systemdesign.ai.mapper.EvaluationMapper;
import com.backend.systemdesign.ai.model.Answer;
import com.backend.systemdesign.ai.model.Evaluation;
import com.backend.systemdesign.ai.model.EvaluationStatus;
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
        Answer answer = findAnswer(answerId);

        Evaluation evaluation = evaluationRepository.findByAnswer(answer)
                .orElseGet(Evaluation::new);

        if (isEvaluationFresh(evaluation, answer)) {
            return evaluationMapper.toResponse(evaluation);
        }

        return evaluate(answer, evaluation);
    }

    public EvaluationResponse retryEvaluation(long answerId) {
        Answer answer = findAnswer(answerId);

        Evaluation evaluation = evaluationRepository.findByAnswer(answer)
                .orElseGet(Evaluation::new);

        return evaluate(answer, evaluation);
    }

    private EvaluationResponse evaluate(Answer answer, Evaluation evaluation) {
        evaluation.setStatus(EvaluationStatus.PROCESSING);
        evaluationEngine.evaluate(answer, evaluation);
        return evaluationMapper.toResponse(evaluationRepository.save(evaluation));
    }

    private Answer findAnswer(long answerId) {
        return answerRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found"));
    }

    private boolean isEvaluationFresh(Evaluation evaluation, Answer answer) {
        if (evaluation.getEvaluatedAt() == null) {
            return false;
        }
        return !evaluation.getEvaluatedAt().isBefore(answer.getUpdatedAt());
    }
}
