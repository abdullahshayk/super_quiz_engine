package com.example.server_quiz_app.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class QuestionCategoryId implements Serializable {
    private static final long serialVersionUID = 5410468377194957371L;
    @Column(name = "category_category_id", nullable = false)
    private Integer categoryCategoryId;

    @Column(name = "question_question_id", nullable = false)
    private Integer questionQuestionId;

    public Integer getCategoryCategoryId() {
        return categoryCategoryId;
    }

    public void setCategoryCategoryId(Integer categoryCategoryId) {
        this.categoryCategoryId = categoryCategoryId;
    }

    public Integer getQuestionQuestionId() {
        return questionQuestionId;
    }

    public void setQuestionQuestionId(Integer questionQuestionId) {
        this.questionQuestionId = questionQuestionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        QuestionCategoryId entity = (QuestionCategoryId) o;
        return Objects.equals(this.questionQuestionId, entity.questionQuestionId) &&
                Objects.equals(this.categoryCategoryId, entity.categoryCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionQuestionId, categoryCategoryId);
    }

}