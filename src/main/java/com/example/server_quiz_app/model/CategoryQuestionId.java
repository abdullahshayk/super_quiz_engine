package com.example.server_quiz_app.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CategoryQuestionId implements Serializable {
    private static final long serialVersionUID = 289316642828935977L;
    @Column(name = "category_category_id", nullable = false)
    private Integer categoryCategoryId;

    @Column(name = "questions_question_id", nullable = false)
    private Integer questionsQuestionId;

    public Integer getCategoryCategoryId() {
        return categoryCategoryId;
    }

    public void setCategoryCategoryId(Integer categoryCategoryId) {
        this.categoryCategoryId = categoryCategoryId;
    }

    public Integer getQuestionsQuestionId() {
        return questionsQuestionId;
    }

    public void setQuestionsQuestionId(Integer questionsQuestionId) {
        this.questionsQuestionId = questionsQuestionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CategoryQuestionId entity = (CategoryQuestionId) o;
        return Objects.equals(this.questionsQuestionId, entity.questionsQuestionId) &&
                Objects.equals(this.categoryCategoryId, entity.categoryCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionsQuestionId, categoryCategoryId);
    }

}