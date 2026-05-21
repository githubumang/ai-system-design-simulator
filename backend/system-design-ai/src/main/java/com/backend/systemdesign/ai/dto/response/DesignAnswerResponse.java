package com.backend.systemdesign.ai.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignAnswerResponse {

    private Long id;
    private Long questionId;
    private String questionTitle;
    private String functionalRequirements;
    private String nonFunctionalRequirements;
    private String apiDesign;
    private String databaseDesign;
    private String scalingStrategy;
    private String tradeOffs;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
