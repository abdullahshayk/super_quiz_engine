package com.example.server_quiz_app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "`option`")
public class Option {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private OptionId id;

    @MapsId("questionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    @JsonBackReference
    private Question question;

    @Column(name = "`option`", nullable = false, length = 50)
    private String option;

    @Column(name = "is_correct", nullable = false)
    private Byte isCorrect;


}