package com.example.server_quiz_app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Integer id;

    @Column(name = "category_name", nullable = true, length = 45)
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
    @JsonIgnore
    private Set<Question> questions = new LinkedHashSet<>();

}