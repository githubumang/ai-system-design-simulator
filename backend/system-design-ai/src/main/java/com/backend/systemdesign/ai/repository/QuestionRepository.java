package com.backend.systemdesign.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.systemdesign.ai.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
