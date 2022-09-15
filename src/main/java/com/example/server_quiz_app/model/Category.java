package com.example.server_quiz_app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Integer id;

    @Column(name = "category_name", nullable = false, length = 45)
    private String categoryName;

    @ManyToMany
    @JoinTable(name = "student_category",
            joinColumns = @JoinColumn(name = "category_category_id"),
            inverseJoinColumns = @JoinColumn(name = "student_student_id"))
    @JsonIgnore
    private Set<Student> students = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "question_category",
            joinColumns = @JoinColumn(name = "category_category_id"),
            inverseJoinColumns = @JoinColumn(name = "question_question_id"))
    @JsonBackReference
    private Set<Question> questions = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

}