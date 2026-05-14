package com.backend.systemdesign.ai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.systemdesign.ai.dto.response.AnswerResponse;
import com.backend.systemdesign.ai.service.AnswerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/answers")
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<AnswerResponse> submitAnswer(@RequestParam String questionId, @RequestParam String answer) {
        //TODO: process POST request
        AnswerResponse responseDto = answerService.saveAnswer(questionId, answer);
        return ResponseEntity.ok(responseDto);
    }

}
