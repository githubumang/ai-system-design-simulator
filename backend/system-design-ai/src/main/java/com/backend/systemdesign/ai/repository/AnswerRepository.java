package com.backend.systemdesign.ai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.systemdesign.ai.model.Answer;
import com.backend.systemdesign.ai.model.Question;
import com.backend.systemdesign.ai.model.User;

public interface AnswerRepository extends JpaRepository <Answer, Long>  {

    Optional<Answer> findByUserAndQuestion(User user, Question question);

}
