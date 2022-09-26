package com.example.server_quiz_app.model;

import javax.persistence.*;

@Entity
@Table(name = "teacher_category")
public class TeacherCategory {
    @EmbeddedId
    private TeacherCategoryId id;

    @MapsId("categoryCategoryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_category_id", nullable = false)
    private Category categoryCategory;

    @MapsId("teacherTeacherId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_teacher_id", nullable = false)
    private Teacher teacherTeacher;

    public TeacherCategoryId getId() {
        return id;
    }

    public void setId(TeacherCategoryId id) {
        this.id = id;
    }

    public Category getCategoryCategory() {
        return categoryCategory;
    }

    public void setCategoryCategory(Category categoryCategory) {
        this.categoryCategory = categoryCategory;
    }

    public Teacher getTeacherTeacher() {
        return teacherTeacher;
    }

    public void setTeacherTeacher(Teacher teacherTeacher) {
        this.teacherTeacher = teacherTeacher;
    }

}