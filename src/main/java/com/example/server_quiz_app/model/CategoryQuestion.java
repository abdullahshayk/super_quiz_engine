package com.example.server_quiz_app.model;

import javax.persistence.*;

@Entity
@Table(name = "category_questions")
public class CategoryQuestion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private CategoryQuestionId id;

    @MapsId("categoryCategoryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_category_id", nullable = false)
    private Category categoryCategory;

    @MapsId("questionsQuestionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "questions_question_id", nullable = false)
    private Question questionsQuestion;

    public CategoryQuestionId getId() {
        return id;
    }

    public void setId(CategoryQuestionId id) {
        this.id = id;
    }

    public Category getCategoryCategory() {
        return categoryCategory;
    }

    public void setCategoryCategory(Category categoryCategory) {
        this.categoryCategory = categoryCategory;
    }

    public Question getQuestionsQuestion() {
        return questionsQuestion;
    }

    public void setQuestionsQuestion(Question questionsQuestion) {
        this.questionsQuestion = questionsQuestion;
    }

}