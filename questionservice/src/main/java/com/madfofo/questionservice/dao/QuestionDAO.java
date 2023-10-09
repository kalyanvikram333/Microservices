package com.madfofo.questionservice.dao;

import com.madfofo.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question,Integer> {
    public List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id FROM QUESTION q WHERE q.category=:category ORDER BY RAND() LIMIT :noOfQuestions", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int noOfQuestions);
}
