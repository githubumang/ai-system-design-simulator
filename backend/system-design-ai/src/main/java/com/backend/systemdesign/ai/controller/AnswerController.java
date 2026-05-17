package com.backend.systemdesign.ai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.systemdesign.ai.dto.request.DesignAnswerRequest;
import com.backend.systemdesign.ai.dto.request.TheoryAnswerRequest;
import com.backend.systemdesign.ai.dto.response.DesignAnswerResponse;
import com.backend.systemdesign.ai.dto.response.TheoryAnswerResponse;
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

    @PostMapping("/theory")
    @Operation(summary = "Submit answer for a theory question")
    public ResponseEntity<TheoryAnswerResponse> submitTheoryAnswer(@Valid @RequestBody TheoryAnswerRequest requestDto) {

        TheoryAnswerResponse responseDto = answerService.submitOrUpdateTheoryAnswer(requestDto);
        return ResponseEntity.ok(responseDto);
        
    }

    @PostMapping("/design")
    @Operation(summary = "Submit answer for a design question")
    public ResponseEntity<DesignAnswerResponse> submitDesignAnswer(@Valid @RequestBody DesignAnswerRequest requestDto) {

        DesignAnswerResponse responseDto = answerService.submitOrUpdateDesignAnswer(requestDto);
        return ResponseEntity.ok(responseDto);
        
    }

}
