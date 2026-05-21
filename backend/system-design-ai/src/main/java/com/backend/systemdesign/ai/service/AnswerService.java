package com.backend.systemdesign.ai.service;

import com.backend.systemdesign.ai.dto.request.DesignAnswerRequest;
import com.backend.systemdesign.ai.dto.request.TheoryAnswerRequest;
import com.backend.systemdesign.ai.dto.response.DesignAnswerResponse;
import com.backend.systemdesign.ai.dto.response.TheoryAnswerResponse;
import com.backend.systemdesign.ai.exception.InvalidQuestionTypeException;
import com.backend.systemdesign.ai.exception.ResourceNotFoundException;
import com.backend.systemdesign.ai.mapper.AnswerMapper;
import com.backend.systemdesign.ai.model.Answer;
import com.backend.systemdesign.ai.model.Question;
import com.backend.systemdesign.ai.model.QuestionType;
import com.backend.systemdesign.ai.model.User;
import com.backend.systemdesign.ai.repository.AnswerRepository;
import com.backend.systemdesign.ai.repository.QuestionRepository;
import com.backend.systemdesign.ai.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    private final QuestionRepository questionRepository;

    private final UserRepository userRepository;

    private final AnswerMapper answerMapper;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository,
            UserRepository userRepository, AnswerMapper answerMapper) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.answerMapper = answerMapper;
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

        Answer answer = answerRepository.findByUserAndQuestion(user, question)
                .orElseGet(() -> {
                    Answer a = new Answer();
                    a.setUser(user);
                    a.setQuestion(question);
                    return a;
                });

        answer.setUserAnswer(requestDto.getAnswer());

        Answer savedAnswer = answerRepository.save(answer);

        return answerMapper.toTheoryResponse(savedAnswer);
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

        Answer answer = answerRepository.findByUserAndQuestion(user, question)
                .orElseGet(() -> {
                    Answer a = new Answer();
                    a.setUser(user);
                    a.setQuestion(question);
                    return a;
                });

        answer.setApiDesign(requestDto.getApiDesign());
        answer.setDatabaseDesign(requestDto.getDatabaseDesign());
        answer.setNonFunctionalRequirements(requestDto.getNonFunctionalRequirements());
        answer.setFunctionalRequirements(requestDto.getFunctionalRequirements());
        answer.setScalingStrategy(requestDto.getScalingStrategy());
        answer.setTradeOffs(requestDto.getTradeOffs());

        Answer savedAnswer = answerRepository.save(answer);

        return answerMapper.toDesignResponse(savedAnswer);
    }
}