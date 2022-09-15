package com.example.server_quiz_app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "question_category")
public class QuestionCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private QuestionCategoryId id;

    @MapsId("categoryCategoryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_category_id", nullable = false)
    private Category categoryCategory;
}