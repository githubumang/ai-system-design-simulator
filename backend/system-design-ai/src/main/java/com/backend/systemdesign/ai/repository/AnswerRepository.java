package com.backend.systemdesign.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.systemdesign.ai.model.Answer;

public interface AnswerRepository extends JpaRepository <Answer, Long>  {

}
