package com.backend.systemdesign.ai.service;

import com.backend.systemdesign.ai.dto.response.AnswerResponse;
import com.backend.systemdesign.ai.model.Answer;
import com.backend.systemdesign.ai.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public AnswerResponse saveAnswer(String questionId, String userAnswer) {

        String feedback = generateDummyFeedback(userAnswer);

        Answer answer = new Answer();
        answer.setQuestionId(questionId);
        answer.setUserAnswer(userAnswer);
        answer.setFeedback(feedback);
        answer.setCreatedAt(LocalDateTime.now());

        Answer savedAnswer = answerRepository.save(answer);

        AnswerResponse responseDto = new AnswerResponse();
        responseDto.setId(savedAnswer.getId());
        responseDto.setAnswer(savedAnswer.getUserAnswer());
        responseDto.setCreatedAt(savedAnswer.getCreatedAt());
        responseDto.setFeedback(savedAnswer.getFeedback());
        responseDto.setQuestionId(savedAnswer.getQuestionId());

        return responseDto;
    }

    private String generateDummyFeedback(String userAnswer) {
        return "Good attempt. AI feedback will be added later.";
    }
}