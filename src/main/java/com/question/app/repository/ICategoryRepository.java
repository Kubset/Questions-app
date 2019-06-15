package com.question.app.repository;

import com.question.app.model.Category;
import com.question.app.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAll();

    Category findById(@Param("id") long id);

//    Category findByName(@Param("name") String name);
    Optional<Category> findByName(String name);



}
