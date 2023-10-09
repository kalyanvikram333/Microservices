package com.madfofo.quizzservice.fieng;

import com.madfofo.quizzservice.model.QuestionWrapper;
import com.madfofo.quizzservice.model.Reponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int nQues);

    //give questions based on question id
    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionsID);
    //calculate result

    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Reponse> responses);

}
