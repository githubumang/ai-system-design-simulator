package com.backend.systemdesign.ai.mapper;

import org.springframework.stereotype.Component;

import com.backend.systemdesign.ai.dto.ai.AIEvaluationResult;
import com.backend.systemdesign.ai.model.Answer;
import com.backend.systemdesign.ai.model.Evaluation;
import com.backend.systemdesign.ai.model.QuestionType;

@Component
public class AIEvaluationMapper {

    public void mapToEvaluation(AIEvaluationResult result, Evaluation evaluation, Answer answer) {

        // Common fields
        evaluation.setOverallScore(result.getOverallScore());
        evaluation.setStrengths(result.getStrengths());
        evaluation.setWeaknesses(result.getWeaknesses());
        evaluation.setSuggestions(result.getSuggestions());
        evaluation.setOverallFeedback(result.getOverallFeedback());

        // DESIGN only
        if (answer.getQuestion().getType() == QuestionType.DESIGN) {
            evaluation.setFunctionalRequirementsScore(result.getFunctionalRequirements().getScore());
            evaluation.setFunctionalRequirementsFeedback(result.getFunctionalRequirements().getFeedback());

            evaluation.setNonFunctionalRequirementsScore(result.getNonFunctionalRequirements().getScore());
            evaluation.setNonFunctionalRequirementsFeedback(result.getNonFunctionalRequirements().getFeedback());

            evaluation.setApiDesignScore(result.getApiDesign().getScore());
            evaluation.setApiDesignFeedback(result.getApiDesign().getFeedback());

            evaluation.setDatabaseDesignScore(result.getDatabaseDesign().getScore());
            evaluation.setDatabaseDesignFeedback(result.getDatabaseDesign().getFeedback());

            evaluation.setScalingScore(result.getScaling().getScore());
            evaluation.setScalingFeedback(result.getScaling().getFeedback());

            evaluation.setTradeOffsScore(result.getTradeOffs().getScore());
            evaluation.setTradeOffsFeedback(result.getTradeOffs().getFeedback());
        }
    }
}