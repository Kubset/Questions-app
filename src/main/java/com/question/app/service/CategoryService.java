package com.question.app.service;

import com.question.app.model.Category;
import com.question.app.model.Question;
import com.question.app.repository.ICategoryRepository;
import com.question.app.repository.IQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class CategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IQuestionRepository questionRepository;

    public Category saveCategory(Category category) {
        if(isNull(category.getSuperCategory())) {
            System.out.println("its main category");


        } else {
            System.out.println("has superCategory");
            category.getSuperCategory()
                    .setId(categoryRepository
                    .findByName(category.getSuperCategory().getName())
                    .orElseThrow(IndexOutOfBoundsException::new)
                    .getId());
            System.out.println(category.getId());
        }
        if(isNull(category.getSubCategories())) {
            System.out.println("exmpty subcategories");
            categoryRepository.save(category);
        } else {
            System.out.println("has subcategories");
            List<Category> categories = category.getSubCategories()
                    .stream()
                    .map(x -> categoryRepository.findByName(x.getName())
                            .orElseThrow(IndexOutOfBoundsException::new))
                    .collect(Collectors.toList());

            category.setSubCategories(categories);
            categoryRepository.save(category);
        }
        return category;

    }

    public Optional<Category> getByName(String name) {
        return categoryRepository.findByName(name);
    }

    public String getStringTreeByName(String name) {
        Category c = getByName(name).get();
        StringBuilder sb = new StringBuilder();
        sb.append(c.getName()).append("\n");
        sb.append(printCategories(c.getSubCategories(), "|----"));

        return sb.toString();

    }

    public Category getTreeByName(String name) {
        return getByName(name).orElse(new Category());
    }

    public List<Category> flatListByName(String name) {
        return flatTree(getTreeByName(name));

    }

    //TODO: move to utils or sth else
    public List<Category> flatTree(Category category) {
        List<Category> categories = new ArrayList<>();
        categories.add(category);

        if(category.getSubCategories().size() != 0) {
          category.getSubCategories().forEach(c -> categories.addAll(flatTree(c)));
        }

        return categories;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


     private String printCategories(List<Category> categories, String index) {
        StringBuilder s = new StringBuilder();
        for(Category c: categories) {
            s.append(index).append(c.getName()).append("\n");

                List<Question> questions = questionRepository.getQuestionsByCategoryName(c.getName());
                s.append(printQuestions(questions, index.replaceAll("\\|----", "     ") + " +"));
                s.append(printCategories(c.getSubCategories(), index.replaceAll("\\|----", "     ") + "|----"));
        }

        return s.toString();
    }

    private String printQuestions(List<Question> questions, String index) {
        StringBuilder sb = new StringBuilder();
        for(Question q: questions) {
            sb.append(index).append(q.getQuestion()).append("\n");
        }
        return sb.toString();
    }
}
