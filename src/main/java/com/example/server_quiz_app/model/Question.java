package com.example.server_quiz_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Integer id;

    @Column(name = "difficulty", nullable = false)
    private Integer difficulty;

    @Column(name = "image_url", length = 245)
    private String imageUrl;

    @Column(name = "question_type", nullable = false)
    private Integer questionType;

    @Column(name = "title", length = 245)
    private String title;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "question_category",
            joinColumns = @JoinColumn(name = "question_question_id"),
            inverseJoinColumns = @JoinColumn(name = "category_category_id"))
    private List<Category> categories = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy ="question" )
    @JsonManagedReference
    private List<Option> options = new ArrayList<>();

    @ManyToMany(mappedBy ="likedQuestion")
    private List<Student> likedStudent = new ArrayList<>();

    @ManyToMany(mappedBy ="attemptedQuestion" )
    @JsonIgnore
    private List<Student> attemptedByStudents = new ArrayList<>();

}