package com.question.app.controller;

import com.question.app.model.Question;
import com.question.app.model.ScheduledEmail;
import com.question.app.repository.IScheduledEmailRepository;
import com.question.app.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {

    QuestionService questionService;

    @Autowired
    IScheduledEmailRepository scheduledEmailsRepository;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuesions();
    }

    @PostMapping
    public ResponseEntity<Question> saveQuestion(@RequestBody Question question) {
        questionService.saveQuestion(question);
        return ResponseEntity.status(HttpStatus.OK).body(question);
    }

    @GetMapping("/test")
    public List<ScheduledEmail> testGet() {
        return scheduledEmailsRepository.findAll();

    }
}
