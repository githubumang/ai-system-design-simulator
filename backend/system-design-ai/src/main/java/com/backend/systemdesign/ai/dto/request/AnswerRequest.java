package com.backend.systemdesign.ai.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerRequest {

    @NotBlank(message = "Question Id can not be blank")
    private String questionId;

    @NotBlank(message = "Answer can not be empty")
    @Size(min = 10, message = "Minimum length of answer in 10")
    private String answer;

}
