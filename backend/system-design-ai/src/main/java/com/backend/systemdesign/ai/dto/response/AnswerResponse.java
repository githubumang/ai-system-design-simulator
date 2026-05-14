package com.backend.systemdesign.ai.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerResponse {
    private long id;
    private String questionId;
    private String answer;
    private String feedback;
    private LocalDateTime createdAt;
}
