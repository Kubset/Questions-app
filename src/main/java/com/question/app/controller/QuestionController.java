package com.question.app.controller;

import com.question.app.model.Question;
import com.question.app.model.ScheduledEmail;
import com.question.app.repository.IScheduledEmailRepository;
import com.question.app.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//TODO: remove getAllQuestions endpoint when will be not used
//TODO: add endpoint for move question from one category to another
/**
 * Simple controller for managing questions
 * @author Jakub Setla
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private QuestionService questionService;
    private IScheduledEmailRepository scheduledEmailsRepository;

    @Autowired
    public QuestionController(QuestionService questionService, IScheduledEmailRepository scheduledEmailsRepository) {
        this.questionService = questionService;
        this.scheduledEmailsRepository = scheduledEmailsRepository;
    }


    /**
     * Endpoint for get all questions from application.
     * Will be changed soon when number of possible questions will be bigger
     * @return List questions
     */
    @Deprecated
    @GetMapping
    public List<Question> getAllQuestions() {
        log.warn("Getting all questions");
        return questionService.getAllQuesions();
    }

    /**
     * Creating question
     * @param question
     * @return newly created question with assigned id and default values
     */
    @PostMapping
    public ResponseEntity<Question> saveQuestion(@RequestBody Question question) {
        questionService.saveQuestion(question);
        return ResponseEntity.status(HttpStatus.OK).body(question);
    }

}
