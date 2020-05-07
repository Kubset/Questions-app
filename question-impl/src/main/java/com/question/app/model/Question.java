package com.question.app.model;

//import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    String question;

    Integer points;

    Integer max_points;

    Long insertDate;

    @OneToOne
    Category category;

    public Long getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Long insertDate) {
        this.insertDate = insertDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getMax_points() {
        return max_points;
    }

    public void setMax_points(Integer max_points) {
        this.max_points = max_points;
    }
}

