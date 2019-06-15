package com.question.app.service;

import com.question.app.model.Question;

import java.util.List;

public interface IQuestionService {

    List<Question> getAllQuesions();

    public void saveQuestion(Question question);
}
