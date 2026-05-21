package com.backend.systemdesign.ai.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheoryAnswerResponse {

    private Long id;
    private Long questionId;
    private String questionTitle;
    private String answer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
