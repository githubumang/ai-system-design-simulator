package com.backend.systemdesign.ai.service.ai.implementation;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.backend.systemdesign.ai.model.Answer;
import com.backend.systemdesign.ai.model.Evaluation;
import com.backend.systemdesign.ai.model.QuestionType;
import com.backend.systemdesign.ai.service.ai.EvaluationEngine;

@Service
public class DummyEvaluationEngine implements EvaluationEngine {

    @Override
    public void evaluate(Answer answer, Evaluation evaluation) {

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
            evaluation.setOverallFeedback("Overall feedback is good");
            evaluation.setOverallScore(8);
        }
        evaluation.setEvaluatedAt(LocalDateTime.now());
        evaluation.setAnswer(answer);

    }

}
