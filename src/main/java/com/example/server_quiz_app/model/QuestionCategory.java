package com.example.server_quiz_app.model;

import javax.persistence.*;

@Entity
@Table(name = "question_category")
public class QuestionCategory {
    @EmbeddedId
    private QuestionCategoryId id;

    @MapsId("categoryCategoryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_category_id", nullable = false)
    private Category categoryCategory;

    @MapsId("questionQuestionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_question_id", nullable = false)
    private Question questionQuestion;

    public QuestionCategoryId getId() {
        return id;
    }

    public void setId(QuestionCategoryId id) {
        this.id = id;
    }

    public Category getCategoryCategory() {
        return categoryCategory;
    }

    public void setCategoryCategory(Category categoryCategory) {
        this.categoryCategory = categoryCategory;
    }

    public Question getQuestionQuestion() {
        return questionQuestion;
    }

    public void setQuestionQuestion(Question questionQuestion) {
        this.questionQuestion = questionQuestion;
    }

}