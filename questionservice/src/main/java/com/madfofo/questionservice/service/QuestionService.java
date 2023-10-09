package com.madfofo.questionservice.service;


import com.madfofo.questionservice.dao.QuestionDAO;
import com.madfofo.questionservice.model.Question;
import com.madfofo.questionservice.model.QuestionWrapper;
import com.madfofo.questionservice.model.Reponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDAO.findByCategory(category),HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question q) {
        questionDAO.save(q);
        return new ResponseEntity<>("Sucess",HttpStatus.CREATED);
    }

    public String deleteQuestion(int id) {
        questionDAO.deleteById(id);

        return "sucess";

    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, int nQues) {

        List<Integer> questions=questionDAO.findRandomQuestionsByCategory(category,nQues);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(List<Integer> questionsID) {
        List<QuestionWrapper> wrappers=new ArrayList<>();
        List<Question> questions=new ArrayList<>();
        for(Integer id:questionsID){

            questions.add(questionDAO.findById(id).get());
        }
        for(Question q:questions){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4(),q.getQuestionTitle());
            wrappers.add(qw);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Reponse> responses) {

        int i=0;
        Map<Integer,String> mapQuestion=new HashMap<>();
        for(Reponse l:responses){
            mapQuestion.put(l.getId(),l.getRight_answer());
        }

        for(Reponse res:responses){
            Question q=questionDAO.findById(res.getId()).get();

            if(q.getRightAnswer().equals(res.getRight_answer())){
                i++;
            }

        }
        return new ResponseEntity<>(i,HttpStatus.OK);
    }
}
