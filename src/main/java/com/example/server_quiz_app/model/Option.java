package com.example.server_quiz_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "`option`")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id", nullable = false)
    private Integer id;

    @Column(name = "`option`", nullable = false, length = 50)
    private String option;


    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect;



}