package com.question.app.service;

import com.question.app.model.Category;
import com.question.app.model.Question;
import com.question.app.repository.ICategoryRepository;
import com.question.app.repository.IQuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

@Service
public class QuestionService implements IQuestionService {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    private IQuestionRepository questionRepository;
    private ICategoryRepository categoryRepository;


    @Autowired
    public QuestionService(IQuestionRepository questionRepository, ICategoryRepository categoryRepository) {
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Question> getAllQuesions() {
        return questionRepository.findAll();
    }

    @Override
    public void saveQuestion(Question question) {
        Optional<Category> c = categoryRepository.findByName(question.getCategory().getName());

        if(isNull(question.getInsertDate())) {
            question.setInsertDate(System.currentTimeMillis());
        }

        //not yet implemented
        if (false) {
            System.out.println("error");
        } else {
            question.getCategory().setId(c.get().getId());
        }
        questionRepository.save(question);
    }

    public void updateQuestion(Question question) {
        //TODO: handle incorrect objects
        questionRepository.save(question);
    }

    public List<Question> getQuestionsByCategory(Category category) {
        return questionRepository.getQuestionsByCategoryName(category.getName());
    }

    public List<Question> getQuestionsByCategory(List<Category> categories) {
        return categories
                .stream()
                .map(this::getQuestionsByCategory)
                .flatMap(List::stream)
                .collect(toList());
    }

    public List<Question> getRandomQuestionList(List<Category> categories, int questionsNumber) {
        List<Question> questions = this.getQuestionsByCategory(categories);
        Collections.shuffle(questions);
        if(questions.size() <= questionsNumber) {
            log.warn("not enough questions {}/{}", questions.size(), questionsNumber);
            return questions;
        } else {
           return questions.subList(0, questionsNumber);
        }
    }

    public List<Question> getScoredQuestionList(List<Category> categories, int questionsNumber, float randomnessFactor) {
        List<Question> questions = this.getQuestionsByCategory(categories);

        Collections.sort(questions, new Comparator<Question>() {
            @Override
            public int compare(Question q1, Question q2) {
                return q1.getMax_points() - q2.getMax_points();
            }
        });


        if(questions.size() <= questionsNumber) {
            log.warn("not enough questions {}/{}", questions.size(), questionsNumber);
            return questions;
        } else if(questions.size() <= questionsNumber * randomnessFactor) {
            log.warn("not too high randomness factor {}", randomnessFactor);
        } else {
            questions = questions.subList(0, (int)(questions.size() * randomnessFactor));
        }

        Collections.shuffle(questions);
        return questions.subList(0, questionsNumber);



    }

}
