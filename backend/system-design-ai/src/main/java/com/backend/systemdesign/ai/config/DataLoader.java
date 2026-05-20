package com.backend.systemdesign.ai.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backend.systemdesign.ai.model.Difficulty;
import com.backend.systemdesign.ai.model.Question;
import com.backend.systemdesign.ai.model.QuestionType;
import com.backend.systemdesign.ai.model.User;
import com.backend.systemdesign.ai.repository.QuestionRepository;
import com.backend.systemdesign.ai.repository.UserRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(QuestionRepository questionRepository, UserRepository userRepository) {
        return args -> {
            questionRepository.save(createQuestion("What is System Design?",
                    "Explain System Design and its advantages",
                    QuestionType.THEORY, Difficulty.EASY));

            questionRepository.save(createQuestion("Design URL Shortener",
                    "Design a scalable URL shortening service",
                    QuestionType.DESIGN, Difficulty.MEDIUM));

            userRepository.save(createUser("Umang", "uma@gmail.com"));

            userRepository.save(createUser("Aman", "aman@gmail.com"));
        };
    }

    private Question createQuestion(String title, String desc, QuestionType type, Difficulty difficulty) {
        Question q = new Question();
        q.setTitle(title);
        q.setDescription(desc);
        q.setType(type);
        q.setDifficulty(difficulty);
        return q;
    }

    private User createUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return user;
    }
}
