package com.question.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinTable(
            name = "category_category",
            joinColumns=@JoinColumn(name="cat2_id"),
            inverseJoinColumns=@JoinColumn(name="cat1_id")
    )
    private Category superCategory;

    @OneToMany
    @JoinTable(
        name = "category_category",
        joinColumns=@JoinColumn(name="cat1_id"),
        inverseJoinColumns=@JoinColumn(name="cat2_id")
    )
    private List<Category> subCategories;

    public Category getSuperCategory() {
        return superCategory;
    }

    public void setSuperCategory(Category superCategory) {
        this.superCategory = superCategory;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
