package com.question.app.service;

import com.question.app.model.Category;
import com.question.app.model.Question;
import com.question.app.repository.ICategoryRepository;
import com.question.app.repository.IQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

@Service
public class QuestionService implements IQuestionService {


    @Autowired
    IQuestionRepository questionRepository;

    @Autowired
    ICategoryRepository categoryRepository;


    @Override
    public List<Question> getAllQuesions() {
        return questionRepository.findAll();
    }

    @Override
    public void saveQuestion(Question question) {
        Optional<Category> c = categoryRepository.findByName(question.getCategory().getName());

        if (false) {
            System.out.println("error");
        } else {
            question.getCategory().setId(c.get().getId());
        }
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

}
