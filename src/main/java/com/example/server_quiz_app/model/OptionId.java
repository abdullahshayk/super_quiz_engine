package com.example.server_quiz_app.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OptionId implements Serializable {
    private static final long serialVersionUID = 3331713218291560579L;
    @Column(name = "option_id", nullable = false)
    private Integer optionId;

    @Column(name = "question_id", nullable = false)
    private Integer questionId;

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OptionId entity = (OptionId) o;
        return Objects.equals(this.questionId, entity.questionId) &&
                Objects.equals(this.optionId, entity.optionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, optionId);
    }

}