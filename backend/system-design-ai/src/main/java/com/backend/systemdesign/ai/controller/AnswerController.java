package com.backend.systemdesign.ai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.systemdesign.ai.dto.request.AnswerRequest;
import com.backend.systemdesign.ai.dto.response.AnswerResponse;
import com.backend.systemdesign.ai.service.AnswerService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/answers")
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<AnswerResponse> submitAnswer(@Valid @RequestBody AnswerRequest requestDto) {
        //TODO: process POST request
        AnswerResponse responseDto = answerService.saveAnswer(requestDto);
        return ResponseEntity.ok(responseDto);
    }

}
