package com.backend.systemdesign.ai.service.ai;

import com.backend.systemdesign.ai.model.Answer;
import com.backend.systemdesign.ai.model.Evaluation;

public interface EvaluationEngine {

    void evaluate(Answer answer, Evaluation evaluation);

}
