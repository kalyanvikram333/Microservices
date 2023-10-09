package com.madfofo.quizzservice.dao;

import com.madfofo.quizzservice.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDAO extends JpaRepository<Quiz,Integer> {

}
