package com.madfofo.questionservice.controller;



import com.madfofo.questionservice.model.Question;
import com.madfofo.questionservice.model.QuestionWrapper;
import com.madfofo.questionservice.model.Reponse;
import com.madfofo.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    Environment environment;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return
                questionService.getAllQuestions();
    }


    @GetMapping("category/{topic}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("topic") String category ){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question q){

        return questionService.addQuestion(q);
    }

    @DeleteMapping("delete/{id}")
    public String deleteQuestion(@PathVariable("id") int id){
        return questionService.deleteQuestion(id);
    }

    //generate quiz questions

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int nQues){
        return questionService.getQuestionsForQuiz(category, nQues);

    }

    //give questions based on question id
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionsID){

        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromIds(questionsID);
    }
    //calculate result

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Reponse> responses){
        return questionService.getScore(responses);
    }

}
