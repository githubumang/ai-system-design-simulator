package com.backend.systemdesign.ai.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backend.systemdesign.ai.dto.response.EvaluationResponse;
import com.backend.systemdesign.ai.exception.ResourceNotFoundException;
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


    public EvaluationService(AnswerRepository answerRepository, EvaluationRepository evaluationRepository,
            EvaluationEngine evaluationEngine) {
        this.answerRepository = answerRepository;
        this.evaluationRepository = evaluationRepository;
        this.evaluationEngine = evaluationEngine;
    }

    public EvaluationResponse generateEvaluation (long answerId) {
        Answer answer = answerRepository.findById(answerId)
                                        .orElseThrow(()->
                                            new ResourceNotFoundException("Answer not found")
                                        );
                                    

        Evaluation evaluation ;
        Optional<Evaluation> existing = evaluationRepository.findByAnswer(answer);

        if(existing.isPresent()) {
            evaluation = existing.get();
        }
        else {
            evaluation = new Evaluation();
        }

        evaluationEngine.evaluate(answer, evaluation);

        Evaluation savedEvaluation = evaluationRepository.save(evaluation);

        

        return mapToResponse(savedEvaluation);
    }

    private EvaluationResponse mapToResponse (Evaluation evaluation) {
        EvaluationResponse responseDto = new EvaluationResponse();

        responseDto.setApiDesignScore(evaluation.getApiDesignScore());
        responseDto.setDatabaseDesignScore(evaluation.getDatabaseDesignScore());
        responseDto.setFunctionalRequirementsScore(evaluation.getFunctionalRequirementsScore());
        responseDto.setNonFunctionalRequirementsScore(evaluation.getNonFunctionalRequirementsScore());
        responseDto.setScalingScore(evaluation.getScalingScore());
        responseDto.setTradeOffsScore(evaluation.getTradeOffsScore());
        responseDto.setOverallFeedback(evaluation.getOverallFeedback());
        responseDto.setOverallScore(evaluation.getOverallScore());
        responseDto.setAnswerId(evaluation.getAnswer().getId());
        responseDto.setEvaluatedAt(evaluation.getEvaluatedAt());
        responseDto.setQuestionType(evaluation.getAnswer().getQuestion().getType());

        return responseDto;
    }
}
