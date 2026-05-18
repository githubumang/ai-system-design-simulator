package com.backend.systemdesign.ai.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.backend.systemdesign.ai.model.Question;
import com.backend.systemdesign.ai.service.QuestionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1/questions")
@Tag(name = "Question APIs", description = "APIs for managing interview questions")
public class QuestionContoller {
    private final QuestionService questionService;

    public QuestionContoller(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    @Operation(summary = "Fetch all questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questionList = questionService.getAllQuestions();
        return ResponseEntity.ok(questionList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Fetch question by ID")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        return ResponseEntity.ok(question);
    }
    
    

}
