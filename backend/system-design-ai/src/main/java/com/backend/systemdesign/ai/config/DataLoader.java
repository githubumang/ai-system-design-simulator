package com.backend.systemdesign.ai.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.backend.systemdesign.ai.model.Answer;
import com.backend.systemdesign.ai.model.Evaluation;
import com.backend.systemdesign.ai.model.Question;
import com.backend.systemdesign.ai.model.QuestionType;
import com.backend.systemdesign.ai.model.User;
import com.backend.systemdesign.ai.repository.AnswerRepository;
import com.backend.systemdesign.ai.repository.EvaluationRepository;
import com.backend.systemdesign.ai.repository.QuestionRepository;
import com.backend.systemdesign.ai.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final EvaluationRepository evaluationRepository;

    public DataLoader(UserRepository userRepository,
                      QuestionRepository questionRepository,
                      AnswerRepository answerRepository,
                      EvaluationRepository evaluationRepository) {

        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.evaluationRepository = evaluationRepository;
    }

    @Override
    public void run(String... args) {

        if (userRepository.count() > 0) {
            return;
        }

        // ---------------- USERS ----------------

        User user1 = new User();
        user1.setName("Umang Agrawal");
        user1.setEmail("umang@example.com");

        User user2 = new User();
        user2.setName("John Doe");
        user2.setEmail("john@example.com");

        User user3 = new User();
        user3.setName("Jane Smith");
        user3.setEmail("jane@example.com");

        userRepository.saveAll(List.of(user1, user2, user3));

        // ---------------- QUESTIONS ----------------

        Question q1 = new Question();
        q1.setTitle("What is Consistent Hashing?");
        q1.setDescription("Explain Consistent Hashing and its advantages.");
        q1.setType(QuestionType.THEORY);

        Question q2 = new Question();
        q2.setTitle("Explain CAP Theorem");
        q2.setDescription("Discuss CAP theorem with examples.");
        q2.setType(QuestionType.THEORY);

        Question q3 = new Question();
        q3.setTitle("Difference between SQL and NoSQL");
        q3.setDescription("Compare SQL and NoSQL databases.");
        q3.setType(QuestionType.THEORY);

        Question q4 = new Question();
        q4.setTitle("Design URL Shortener");
        q4.setDescription("Design a scalable URL shortening service.");
        q4.setType(QuestionType.DESIGN);

        Question q5 = new Question();
        q5.setTitle("Design WhatsApp");
        q5.setDescription("Design a scalable messaging application.");
        q5.setType(QuestionType.DESIGN);

        Question q6 = new Question();
        q6.setTitle("Design YouTube");
        q6.setDescription("Design a video streaming platform.");
        q6.setType(QuestionType.DESIGN);

        questionRepository.saveAll(List.of(q1, q2, q3, q4, q5, q6));

        // ---------------- ANSWERS ----------------

        Answer answer1 = new Answer();
        answer1.setUser(user1);
        answer1.setQuestion(q1);
        answer1.setUserAnswer(
                "Consistent hashing distributes keys across nodes and minimizes redistribution when nodes are added or removed.");

        Answer answer2 = new Answer();
        answer2.setUser(user2);
        answer2.setQuestion(q2);
        answer2.setUserAnswer(
                "CAP theorem states that distributed systems can provide only two out of consistency, availability and partition tolerance.");

        Answer answer3 = new Answer();
        answer3.setUser(user1);
        answer3.setQuestion(q4);
        answer3.setFunctionalRequirements(
                "Shorten URLs, redirect users, analytics.");
        answer3.setNonFunctionalRequirements(
                "High availability, scalability, low latency.");
        answer3.setApiDesign(
                "POST /shorten, GET /{shortCode}");
        answer3.setDatabaseDesign(
                "Use relational DB for metadata and cache for hot URLs.");
        answer3.setScalingStrategy(
                "Caching, load balancers, DB sharding.");
        answer3.setTradeOffs(
                "SQL for consistency vs NoSQL for scalability.");

        answerRepository.saveAll(
                List.of(answer1, answer2, answer3));

        // ---------------- EVALUATIONS ----------------

        Evaluation evaluation1 = new Evaluation();
        evaluation1.setAnswer(answer1);
        evaluation1.setOverallScore(8);
        evaluation1.setStrengths(
                "Good explanation of key redistribution.");
        evaluation1.setWeaknesses(
                "Could include virtual nodes.");
        evaluation1.setSuggestions(
                "Discuss hash ring in more detail.");
        evaluation1.setOverallFeedback(
                "Good understanding of consistent hashing.");
        evaluation1.setEvaluatedAt(LocalDateTime.now());

        Evaluation evaluation2 = new Evaluation();
        evaluation2.setAnswer(answer3);
        evaluation2.setFunctionalRequirementsScore(8);
        evaluation2.setFunctionalRequirementsFeedback(
                "Requirements are well covered.");

        evaluation2.setNonFunctionalRequirementsScore(8);
        evaluation2.setNonFunctionalRequirementsFeedback(
                "Good coverage of scalability concerns.");

        evaluation2.setApiDesignScore(7);
        evaluation2.setApiDesignFeedback(
                "Basic APIs are present.");

        evaluation2.setDatabaseDesignScore(7);
        evaluation2.setDatabaseDesignFeedback(
                "Database design is acceptable.");

        evaluation2.setScalingScore(8);
        evaluation2.setScalingFeedback(
                "Scaling strategy is good.");

        evaluation2.setTradeOffsScore(6);
        evaluation2.setTradeOffsFeedback(
                "Trade-offs could be deeper.");

        evaluation2.setOverallScore(8);
        evaluation2.setOverallFeedback(
                "Strong overall design.");

        evaluation2.setEvaluatedAt(LocalDateTime.now());

        evaluationRepository.saveAll(
                List.of(evaluation1, evaluation2));
    }
}
