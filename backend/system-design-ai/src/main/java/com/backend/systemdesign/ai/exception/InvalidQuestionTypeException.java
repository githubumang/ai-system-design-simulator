package com.backend.systemdesign.ai.exception;

public class InvalidQuestionTypeException extends RuntimeException {
    public InvalidQuestionTypeException(String message) {
        super(message);
    }
}
