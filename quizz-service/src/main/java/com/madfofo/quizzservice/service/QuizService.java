package com.madfofo.quizzservice.service;


import com.madfofo.quizzservice.dao.QuizDAO;
import com.madfofo.quizzservice.fieng.QuizInterface;
import com.madfofo.quizzservice.model.QuestionWrapper;
import com.madfofo.quizzservice.model.Quiz;
import com.madfofo.quizzservice.model.Reponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    QuizDAO quizDAO;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int noOfQuestions, String title) {

        List<Integer> questionIds=quizInterface.getQuestionsForQuiz(category,noOfQuestions).getBody();
        Quiz q=new Quiz();
        q.setTitle(title);
        q.setQuestionIds(questionIds);
        quizDAO.save(q);
        return new ResponseEntity<>("Sucess", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizbyId(int id) {

        Optional<Quiz> quiz=quizDAO.findById(id);
        List<Integer> questionIds=quiz.get().getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions=quizInterface.getQuestionsFromIds(questionIds);


        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Reponse> responses) {

        return quizInterface.getScore(responses);

    }
}
