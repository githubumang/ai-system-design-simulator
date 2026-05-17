package com.backend.systemdesign.ai.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheoryAnswerRequest {
    @NotNull(message = "Question ID is required")
    private Long questionId;

    @NotBlank(message = "Answer can not be empty")
    @Size(min = 10, message = "Minimum length of answer in 10")
    private String answer;

    @NotNull(message = "User ID is required")
    private Long userId;
}
