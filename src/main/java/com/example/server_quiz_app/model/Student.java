package com.example.server_quiz_app.model;

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

    @Column(name = "password", nullable = false, length = 250)
    private String password;

    @Column(name = "username", nullable = false, length = 75)
    private String username;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "student_category",
            joinColumns = @JoinColumn(name = "student_student_id"),
            inverseJoinColumns = @JoinColumn(name = "category_category_id"))
    private List<Category> categories = new ArrayList<>();



}