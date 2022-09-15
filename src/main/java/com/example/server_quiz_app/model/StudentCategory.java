package com.example.server_quiz_app.model;

import javax.persistence.*;

@Entity
@Table(name = "student_category")
public class StudentCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private StudentCategoryId id;

    @MapsId("studentStudentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_student_id", nullable = false)
    private Student studentStudent;

    @MapsId("categoryCategoryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_category_id", nullable = false)
    private Category categoryCategory;

    public StudentCategoryId getId() {
        return id;
    }

    public void setId(StudentCategoryId id) {
        this.id = id;
    }

    public Student getStudentStudent() {
        return studentStudent;
    }

    public void setStudentStudent(Student studentStudent) {
        this.studentStudent = studentStudent;
    }

    public Category getCategoryCategory() {
        return categoryCategory;
    }

    public void setCategoryCategory(Category categoryCategory) {
        this.categoryCategory = categoryCategory;
    }

}