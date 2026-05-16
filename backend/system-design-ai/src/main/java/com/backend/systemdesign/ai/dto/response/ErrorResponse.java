package com.backend.systemdesign.ai.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String message;

    public ErrorResponse(LocalDateTime timestamp, int status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

}
