package com.example.server_quiz_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "password", nullable = false, length = 75)
    private String password;

    @Column(name = "username", nullable = false, length = 75)
    private String username;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "student_category",
            joinColumns = @JoinColumn(name = "student_student_id"),
            inverseJoinColumns = @JoinColumn(name = "category_category_id"))
    @JsonIgnore
    private List<Category> categories = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "student_like_question",
            joinColumns = @JoinColumn(name = "student_student_id"),
            inverseJoinColumns = @JoinColumn(name = "question_question_id"))
    @JsonIgnore
    private List<Question> likedQuestion = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinTable(name = "student_attempted_question",
            joinColumns = @JoinColumn(name = "student_student_id"),
            inverseJoinColumns = @JoinColumn(name = "question_question_id"))
    @JsonIgnore
    private List<Question> attemptedQuestion = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "student_follow_teacher",
            joinColumns = @JoinColumn(name = "student_student_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_teacher_id"))
    @JsonIgnore
    private List<Teacher> followedTeachers = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "student_like_post",
            joinColumns = @JoinColumn(name = "student_student_id"),
            inverseJoinColumns = @JoinColumn(name = "post_post_id"))
    @JsonIgnore
    private List<Post> likedPosts = new ArrayList<>();








}