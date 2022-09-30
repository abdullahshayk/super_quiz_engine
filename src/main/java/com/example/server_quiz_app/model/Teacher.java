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
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "teacher_category",
            joinColumns = @JoinColumn(name = "teacher_teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "category_category_id"))
    @JsonIgnore
    private List<Category> categories = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "teacher")
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();

    @ManyToMany(mappedBy = "followedTeachers")
    @JsonIgnore
    private List<Student> studentsFollowed = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "teacher")
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();


}