package com.backend.systemdesign.ai.mapper;

import org.springframework.stereotype.Component;

import com.backend.systemdesign.ai.dto.response.DesignAnswerResponse;
import com.backend.systemdesign.ai.dto.response.TheoryAnswerResponse;
import com.backend.systemdesign.ai.model.Answer;

@Component
public class AnswerMapper {

    public TheoryAnswerResponse toTheoryResponse(Answer answer) {
        TheoryAnswerResponse responseDto = new TheoryAnswerResponse();
        responseDto.setId(answer.getId());
        responseDto.setAnswer(answer.getUserAnswer());
        responseDto.setQuestionId(answer.getQuestion().getId());
        responseDto.setQuestionTitle(answer.getQuestion().getTitle());
        responseDto.setCreatedAt(answer.getCreatedAt());
        responseDto.setUpdatedAt(answer.getUpdatedAt());
        return responseDto;
    }

    public DesignAnswerResponse toDesignResponse(Answer answer) {
        DesignAnswerResponse responseDto = new DesignAnswerResponse();
        responseDto.setId(answer.getId());
        responseDto.setQuestionId(answer.getQuestion().getId());
        responseDto.setQuestionTitle(answer.getQuestion().getTitle());
        responseDto.setFunctionalRequirements(answer.getFunctionalRequirements());
        responseDto.setNonFunctionalRequirements(answer.getNonFunctionalRequirements());
        responseDto.setApiDesign(answer.getApiDesign());
        responseDto.setDatabaseDesign(answer.getDatabaseDesign());
        responseDto.setScalingStrategy(answer.getScalingStrategy());
        responseDto.setTradeOffs(answer.getTradeOffs());
        responseDto.setCreatedAt(answer.getCreatedAt());
        responseDto.setUpdatedAt(answer.getUpdatedAt());
        return responseDto;
    }
}