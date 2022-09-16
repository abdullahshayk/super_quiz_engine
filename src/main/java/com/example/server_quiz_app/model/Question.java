package com.example.server_quiz_app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Integer id;

    @Column(name = "title", length = 245)
    private String title;

    @Column(name = "image_url", length = 245)
    private String imageUrl;

    @Column(name = "difficulty", nullable = false)
    private Integer difficulty;

    @Column(name = "questionType", nullable = false)
    private Integer questionType;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "question_category",
            joinColumns = @JoinColumn(name = "question_question_id"),
            inverseJoinColumns = @JoinColumn(name = "category_category_id"))
    private List<Category> categories = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", nullable = false)
    private List<Option> options = new ArrayList<>();

}