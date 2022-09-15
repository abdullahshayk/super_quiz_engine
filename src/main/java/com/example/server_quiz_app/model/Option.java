package com.example.server_quiz_app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
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

    public OptionId getId() {
        return id;
    }

    public void setId(OptionId id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Byte getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Byte isCorrect) {
        this.isCorrect = isCorrect;
    }

}