package com.backend.systemdesign.ai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.systemdesign.ai.model.Question;
import com.backend.systemdesign.ai.repository.QuestionRepository;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found"));
    }
}
