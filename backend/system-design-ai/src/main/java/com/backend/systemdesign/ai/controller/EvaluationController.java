package com.backend.systemdesign.ai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.systemdesign.ai.dto.response.EvaluationResponse;
import com.backend.systemdesign.ai.service.EvaluationService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/evaluations")
public class EvaluationController {

    EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/{answerId}")
    @Operation(summary = "Generate evaluation for answer")
    public ResponseEntity<EvaluationResponse> generateEvaluation(@PathVariable Long answerId) {
        EvaluationResponse response = evaluationService.generateEvaluation(answerId);

        return ResponseEntity.ok(response);
    }
}
