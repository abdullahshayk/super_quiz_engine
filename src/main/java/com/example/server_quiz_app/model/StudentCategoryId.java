package com.example.server_quiz_app.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentCategoryId implements Serializable {
    private static final long serialVersionUID = 1078247227716187288L;
    @Column(name = "category_category_id", nullable = false)
    private Integer categoryCategoryId;

    @Column(name = "student_student_id", nullable = false)
    private Integer studentStudentId;

    public Integer getCategoryCategoryId() {
        return categoryCategoryId;
    }

    public void setCategoryCategoryId(Integer categoryCategoryId) {
        this.categoryCategoryId = categoryCategoryId;
    }

    public Integer getStudentStudentId() {
        return studentStudentId;
    }

    public void setStudentStudentId(Integer studentStudentId) {
        this.studentStudentId = studentStudentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentCategoryId entity = (StudentCategoryId) o;
        return Objects.equals(this.studentStudentId, entity.studentStudentId) &&
                Objects.equals(this.categoryCategoryId, entity.categoryCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentStudentId, categoryCategoryId);
    }

}