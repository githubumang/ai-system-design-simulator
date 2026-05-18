package com.backend.systemdesign.ai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backend.systemdesign.ai.dto.response.EvaluationResponse;
import com.backend.systemdesign.ai.exception.ResourceNotFoundException;
import com.backend.systemdesign.ai.model.Answer;
import com.backend.systemdesign.ai.model.Evaluation;
import com.backend.systemdesign.ai.model.QuestionType;
import com.backend.systemdesign.ai.repository.AnswerRepository;
import com.backend.systemdesign.ai.repository.EvaluationRepository;

@Service
public class EvaluationService {

    private final AnswerRepository answerRepository;

    private final EvaluationRepository evaluationRepository;

    

    public EvaluationService(AnswerRepository answerRepository, EvaluationRepository evaluationRepository) {
        this.answerRepository = answerRepository;
        this.evaluationRepository = evaluationRepository;
    }



    public EvaluationResponse generateEvaluation (long answerId) {
        Answer answer = answerRepository.findById(answerId)
                                        .orElseThrow(()->
                                            new ResourceNotFoundException("Answer not found")
                                        );
                                    

        Optional<Evaluation> existing =
    evaluationRepository.findByAnswer(answer);

        Evaluation evaluation;
                                    
        if(existing.isPresent()) {
            evaluation = existing.get();
        }
        else {
            evaluation = new Evaluation();
        }

        if(answer.getQuestion().getType() == QuestionType.DESIGN) {
            evaluation.setApiDesignScore(4);
            evaluation.setDatabaseDesignScore(5);
            evaluation.setFunctionalRequirementsScore(3);
            evaluation.setNonFunctionalRequirementsScore(2);
            evaluation.setOverallScore(4);
            evaluation.setScalingScore(9);
            evaluation.setTradeOffsScore(10);
            evaluation.setOverallFeedback("Overall feedback is good");
        }
        else {
            evaluation.setApiDesignScore(null);
            evaluation.setDatabaseDesignScore(null);
            evaluation.setFunctionalRequirementsScore(null);
            evaluation.setNonFunctionalRequirementsScore(null);
            evaluation.setScalingScore(null);
            evaluation.setTradeOffsScore(null);
            evaluation.setOverallFeedback("Over feedback is good");
            evaluation.setOverallScore(8);
        }
        evaluation.setEvaluatedAt(LocalDateTime.now());
        evaluation.setAnswer(answer);

        Evaluation savedEvaluation = evaluationRepository.save(evaluation);

        EvaluationResponse responseDto = new EvaluationResponse();

        responseDto.setApiDesignScore(savedEvaluation.getApiDesignScore());
        responseDto.setDatabaseDesignScore(savedEvaluation.getDatabaseDesignScore());
        responseDto.setFunctionalRequirementsScore(savedEvaluation.getFunctionalRequirementsScore());
        responseDto.setNonFunctionalRequirementsScore(savedEvaluation.getNonFunctionalRequirementsScore());
        responseDto.setScalingScore(savedEvaluation.getScalingScore());
        responseDto.setTradeOffsScore(savedEvaluation.getTradeOffsScore());
        responseDto.setOverallFeedback(savedEvaluation.getOverallFeedback());
        responseDto.setOverallScore(savedEvaluation.getOverallScore());
        responseDto.setAnswerId(answerId);
        responseDto.setEvaluatedAt(savedEvaluation.getEvaluatedAt());
        responseDto.setQuestionType(answer.getQuestion().getType());

        return responseDto;
    }
}
