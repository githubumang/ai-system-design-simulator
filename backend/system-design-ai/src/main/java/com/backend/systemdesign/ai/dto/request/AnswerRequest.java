package com.backend.systemdesign.ai.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerRequest {

    private String questionId;

    private String answer;

}
