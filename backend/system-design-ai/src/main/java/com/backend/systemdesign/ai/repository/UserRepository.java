package com.backend.systemdesign.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.systemdesign.ai.model.User;

public interface UserRepository extends JpaRepository<User, Long>  {

}
