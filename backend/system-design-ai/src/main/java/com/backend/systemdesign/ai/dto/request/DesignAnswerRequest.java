package com.backend.systemdesign.ai.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignAnswerRequest {
    @NotNull(message = "Question ID is required")
    private Long questionId;

    @NotBlank(message = "Answer can not be empty")
    @Size(min = 10, message = "Minimum length of functionalRequirements in 10")
    private String functionalRequirements;

    @NotBlank(message = "Answer can not be empty")
    @Size(min = 10, message = "Minimum length of nonFunctionalRequirements in 10")
    private String nonFunctionalRequirements;

    @NotBlank(message = "Answer can not be empty")
    @Size(min = 10, message = "Minimum length of apiDesign in 10")
    private String apiDesign;

    @NotBlank(message = "Answer can not be empty")
    @Size(min = 10, message = "Minimum length of databaseDesign in 10")
    private String databaseDesign;

    @NotBlank(message = "Answer can not be empty")
    @Size(min = 10, message = "Minimum length of scalingStrategy in 10")
    private String scalingStrategy;

    @NotBlank(message = "Answer can not be empty")
    @Size(min = 10, message = "Minimum length of tradeOffs in 10")
    private String tradeOffs;

    @NotNull(message = "User ID is required")
    private Long userId;
}
