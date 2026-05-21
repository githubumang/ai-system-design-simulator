package com.backend.systemdesign.ai.service;

import com.backend.systemdesign.ai.dto.request.DesignAnswerRequest;
import com.backend.systemdesign.ai.dto.request.TheoryAnswerRequest;
import com.backend.systemdesign.ai.dto.response.DesignAnswerResponse;
import com.backend.systemdesign.ai.dto.response.TheoryAnswerResponse;
import com.backend.systemdesign.ai.exception.InvalidQuestionTypeException;
import com.backend.systemdesign.ai.exception.ResourceNotFoundException;
import com.backend.systemdesign.ai.model.Answer;
import com.backend.systemdesign.ai.model.Question;
import com.backend.systemdesign.ai.model.QuestionType;
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

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository,
            UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public TheoryAnswerResponse submitOrUpdateTheoryAnswer(TheoryAnswerRequest requestDto) {

        Question question = questionRepository.findById(
                requestDto.getQuestionId()).orElseThrow(() -> new ResourceNotFoundException("Question not found"));

        User user = userRepository.findById(
                requestDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (question.getType() != QuestionType.THEORY) {
            throw new InvalidQuestionTypeException(
                    "This endpoint only supports THEORY questions");
        }

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

        TheoryAnswerResponse responseDto = new TheoryAnswerResponse();
        responseDto.setId(savedAnswer.getId());
        responseDto.setAnswer(savedAnswer.getUserAnswer());
        responseDto.setCreatedAt(savedAnswer.getCreatedAt());
        responseDto.setFeedback(savedAnswer.getFeedback());
        responseDto.setQuestionId(savedAnswer.getQuestion().getId());
        responseDto.setQuestionTitle(savedAnswer.getQuestion().getTitle());

        return responseDto;
    }

    public DesignAnswerResponse submitOrUpdateDesignAnswer(DesignAnswerRequest requestDto) {

        Question question = questionRepository.findById(
                requestDto.getQuestionId()).orElseThrow(() -> new ResourceNotFoundException("Question not found"));

        User user = userRepository.findById(
                requestDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (question.getType() != QuestionType.DESIGN) {
            throw new InvalidQuestionTypeException(
                    "This endpoint only supports DESIGN questions");
        }

        Optional<Answer> existing = answerRepository.findByUserAndQuestion(user, question);
        String feedback = generateDummyFeedback(requestDto.getFunctionalRequirements());

        Answer answer;

        if (existing.isPresent()) {
            answer = existing.get();
        } else {
            answer = new Answer();
            answer.setUser(user);
            answer.setQuestion(question);
            answer.setCreatedAt(LocalDateTime.now());
        }

        answer.setApiDesign(requestDto.getApiDesign());
        answer.setDatabaseDesign(requestDto.getDatabaseDesign());
        answer.setNonFunctionalRequirements(requestDto.getNonFunctionalRequirements());
        answer.setFunctionalRequirements(requestDto.getFunctionalRequirements());
        answer.setScalingStrategy(requestDto.getScalingStrategy());
        answer.setTradeOffs(requestDto.getTradeOffs());
        answer.setFeedback(feedback);

        Answer savedAnswer = answerRepository.save(answer);

        DesignAnswerResponse responseDto = new DesignAnswerResponse();
        responseDto.setId(savedAnswer.getId());
        responseDto.setApiDesign(savedAnswer.getApiDesign());
        responseDto.setDatabaseDesign(savedAnswer.getDatabaseDesign());
        responseDto.setNonFunctionalRequirements(savedAnswer.getNonFunctionalRequirements());
        responseDto.setFunctionalRequirements(savedAnswer.getFunctionalRequirements());
        responseDto.setScalingStrategy(savedAnswer.getScalingStrategy());
        responseDto.setTradeOffs(savedAnswer.getTradeOffs());
        responseDto.setFeedback(feedback);
        responseDto.setCreatedAt(savedAnswer.getCreatedAt());
        responseDto.setFeedback(savedAnswer.getFeedback());
        responseDto.setQuestionId(savedAnswer.getQuestion().getId());
        responseDto.setQuestionTitle(savedAnswer.getQuestion().getTitle());

        return responseDto;
    }

    private String generateDummyFeedback(String userAnswer) {
        return "Answer submitted successfully. Generate evaluation separately.";
    }
}