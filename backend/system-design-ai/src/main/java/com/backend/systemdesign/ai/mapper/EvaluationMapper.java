package com.backend.systemdesign.ai.mapper;

import org.springframework.stereotype.Component;

import com.backend.systemdesign.ai.dto.response.EvaluationResponse;
import com.backend.systemdesign.ai.model.Evaluation;

@Component
public class EvaluationMapper {

    public EvaluationResponse toResponse(Evaluation evaluation) {

        EvaluationResponse responseDto = new EvaluationResponse();

        responseDto.setApiDesignScore(
                evaluation.getApiDesignScore());

        responseDto.setDatabaseDesignScore(
                evaluation.getDatabaseDesignScore());

        responseDto.setFunctionalRequirementsScore(
                evaluation.getFunctionalRequirementsScore());

        responseDto.setNonFunctionalRequirementsScore(
                evaluation.getNonFunctionalRequirementsScore());

        responseDto.setScalingScore(
                evaluation.getScalingScore());

        responseDto.setTradeOffsScore(
                evaluation.getTradeOffsScore());

        responseDto.setOverallFeedback(
                evaluation.getOverallFeedback());

        responseDto.setOverallScore(
                evaluation.getOverallScore());

        responseDto.setAnswerId(
                evaluation.getAnswer().getId());

        responseDto.setEvaluatedAt(
                evaluation.getEvaluatedAt());

        responseDto.setQuestionType(
                evaluation.getAnswer()
                        .getQuestion()
                        .getType());

        return responseDto;
    }
}