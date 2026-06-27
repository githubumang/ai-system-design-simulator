package com.backend.systemdesign.ai.mapper;

import org.springframework.stereotype.Component;

import com.backend.systemdesign.ai.dto.response.EvaluationResponse;
import com.backend.systemdesign.ai.model.Evaluation;

@Component
public class EvaluationMapper {

        public EvaluationResponse toResponse(Evaluation evaluation) {

                EvaluationResponse responseDto = new EvaluationResponse();

                responseDto.setAnswerId(evaluation.getAnswer().getId());
                responseDto.setQuestionType(evaluation.getAnswer().getQuestion().getType());
                responseDto.setEvaluatedAt(evaluation.getEvaluatedAt());
                responseDto.setStatus(evaluation.getStatus());

                // Common fields
                responseDto.setOverallScore(evaluation.getOverallScore());
                responseDto.setStrengths(evaluation.getStrengths());
                responseDto.setWeaknesses(evaluation.getWeaknesses());
                responseDto.setSuggestions(evaluation.getSuggestions());
                responseDto.setOverallFeedback(evaluation.getOverallFeedback());

                // DESIGN only — section scores + feedback
                responseDto.setFunctionalRequirementsScore(evaluation.getFunctionalRequirementsScore());
                responseDto.setFunctionalRequirementsFeedback(evaluation.getFunctionalRequirementsFeedback());

                responseDto.setNonFunctionalRequirementsScore(evaluation.getNonFunctionalRequirementsScore());
                responseDto.setNonFunctionalRequirementsFeedback(evaluation.getNonFunctionalRequirementsFeedback());

                responseDto.setApiDesignScore(evaluation.getApiDesignScore());
                responseDto.setApiDesignFeedback(evaluation.getApiDesignFeedback());

                responseDto.setDatabaseDesignScore(evaluation.getDatabaseDesignScore());
                responseDto.setDatabaseDesignFeedback(evaluation.getDatabaseDesignFeedback());

                responseDto.setScalingScore(evaluation.getScalingScore());
                responseDto.setScalingFeedback(evaluation.getScalingFeedback());

                responseDto.setTradeOffsScore(evaluation.getTradeOffsScore());
                responseDto.setTradeOffsFeedback(evaluation.getTradeOffsFeedback());

                return responseDto;
        }
}