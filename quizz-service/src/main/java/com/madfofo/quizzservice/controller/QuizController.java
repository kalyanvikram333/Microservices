package com.madfofo.quizzservice.controller;


import com.madfofo.quizzservice.model.QuestionWrapper;
import com.madfofo.quizzservice.model.QuizDTO;
import com.madfofo.quizzservice.model.Reponse;
import com.madfofo.quizzservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController
{
    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO){
        return quizService.createQuiz(quizDTO.getCategoryName(),quizDTO.getNumQuestions(),
                quizDTO.getTitle());

    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable("id") int id){
        return quizService.getQuizbyId(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Reponse> responses){
        return quizService.calculateResult(id, responses);
    }
}
