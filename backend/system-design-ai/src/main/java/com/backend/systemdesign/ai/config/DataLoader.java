package com.backend.systemdesign.ai.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backend.systemdesign.ai.model.Difficulty;
import com.backend.systemdesign.ai.model.Question;
import com.backend.systemdesign.ai.model.QuestionType;
import com.backend.systemdesign.ai.repository.QuestionRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData (QuestionRepository repository) {
        return args -> {
            repository.save(create("What is Spring Boot?",
                    "Explain Spring Boot and its advantages",
                    QuestionType.THEORY, Difficulty.EASY));

            repository.save(create("Design URL Shortener",
                    "Design a scalable URL shortening service",
                    QuestionType.DESIGN, Difficulty.MEDIUM));
        };
    }

    private Question create(String title, String desc, QuestionType type, Difficulty difficulty) {
        Question q = new Question();
        q.setTitle(title);
        q.setDescription(desc);
        q.setType(type);
        q.setDifficulty(difficulty);
        return q;
    }
}
