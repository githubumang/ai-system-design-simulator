package com.backend.systemdesign.ai.service;

import com.backend.systemdesign.ai.model.Answer;
import com.backend.systemdesign.ai.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public Answer saveAnswer(String questionId, String userAnswer) {

        String feedback = generateDummyFeedback(userAnswer);

        Answer answer = new Answer();
        answer.setQuestionId(questionId);
        answer.setUserAnswer(userAnswer);
        answer.setFeedback(feedback);
        answer.setCreatedAt(LocalDateTime.now());

        return answerRepository.save(answer);
    }

    private String generateDummyFeedback(String userAnswer) {
        return "Good attempt. AI feedback will be added later.";
    }
}