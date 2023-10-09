package com.madfofo.quizzservice.model;

import lombok.Data;

@Data
public class QuizDTO {
    String categoryName;
    Integer numQuestions;
    String title;
}
