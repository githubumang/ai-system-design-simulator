package com.backend.systemdesign.ai.service;

import com.backend.systemdesign.ai.dto.request.AnswerRequest;
import com.backend.systemdesign.ai.dto.response.AnswerResponse;
import com.backend.systemdesign.ai.exception.ResourceNotFoundException;
import com.backend.systemdesign.ai.model.Answer;
import com.backend.systemdesign.ai.model.Question;
import com.backend.systemdesign.ai.model.User;
import com.backend.systemdesign.ai.repository.AnswerRepository;
import com.backend.systemdesign.ai.repository.QuestionRepository;
import com.backend.systemdesign.ai.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AnswerService {

    private AnswerRepository answerRepository;

    private QuestionRepository questionRepository;

    private UserRepository userRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository, UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public AnswerResponse submitOrUpdateAnswer(AnswerRequest requestDto) {
        
        Question question = questionRepository.findById(
            requestDto.getQuestionId()
        ).orElseThrow(() ->
                new ResourceNotFoundException("Question not found")
        );

        User user = userRepository.findById(
            requestDto.getUserId()
        ).orElseThrow(() -> 
                new ResourceNotFoundException("User not found")
        );

        Optional<Answer> existing = answerRepository.findByUserAndQuestion(user, question);
        String feedback = generateDummyFeedback(requestDto.getAnswer());

        Answer answer;

        if (existing.isPresent()) {
            answer = existing.get();
        } else {
            answer = new Answer();
            answer.setUser(user);
            answer.setQuestion(question);
            answer.setCreatedAt(LocalDateTime.now());
        }

        answer.setUserAnswer(requestDto.getAnswer());
        answer.setFeedback(feedback);

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