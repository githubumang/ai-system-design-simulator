package com.backend.systemdesign.ai.service;

import com.backend.systemdesign.ai.dto.request.AnswerRequest;
import com.backend.systemdesign.ai.dto.response.AnswerResponse;
import com.backend.systemdesign.ai.exception.ResourceNotFoundException;
import com.backend.systemdesign.ai.model.Answer;
import com.backend.systemdesign.ai.model.Question;
import com.backend.systemdesign.ai.repository.AnswerRepository;
import com.backend.systemdesign.ai.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnswerService {

    private AnswerRepository answerRepository;

    private QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    public AnswerResponse saveAnswer(AnswerRequest requestDto) {

        Question question = questionRepository.findById(
                Long.valueOf(requestDto.getQuestionId())
        ).orElseThrow(() ->
                new ResourceNotFoundException("Question not found")
        );

        String feedback = generateDummyFeedback(requestDto.getAnswer());

        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setUserAnswer(requestDto.getAnswer());
        answer.setFeedback(feedback);
        answer.setCreatedAt(LocalDateTime.now());

        Answer savedAnswer = answerRepository.save(answer);

        AnswerResponse responseDto = new AnswerResponse();
        responseDto.setId(savedAnswer.getId());
        responseDto.setAnswer(savedAnswer.getUserAnswer());
        responseDto.setCreatedAt(savedAnswer.getCreatedAt());
        responseDto.setFeedback(savedAnswer.getFeedback());
        responseDto.setQuestionId(savedAnswer.getQuestion().getId());
        responseDto.setQuestionTitle(savedAnswer.getQuestion().getTitle());

        return responseDto;
    }

    private String generateDummyFeedback(String userAnswer) {
        return "Good attempt. AI feedback will be added later.";
    }
}