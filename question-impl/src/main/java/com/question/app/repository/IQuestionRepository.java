package com.question.app.repository;

import com.question.app.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAll();

    List<Question> getQuestionsByCategoryName(String name);


}
