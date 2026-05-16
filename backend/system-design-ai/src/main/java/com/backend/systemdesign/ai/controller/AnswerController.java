package com.backend.systemdesign.ai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.systemdesign.ai.dto.request.AnswerRequest;
import com.backend.systemdesign.ai.dto.response.AnswerResponse;
import com.backend.systemdesign.ai.service.AnswerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/answers")
@Tag(name = "Answer APIs", description = "APIs for answer submission")
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    @Operation(summary = "Submit answer for a question")
    public ResponseEntity<AnswerResponse> submitAnswer(@Valid @RequestBody AnswerRequest requestDto) {

        AnswerResponse responseDto = answerService.submitOrUpdateAnswer(requestDto);
        return ResponseEntity.ok(responseDto);
        
    }

}
