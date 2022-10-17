package com.example.server_quiz_app.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TeacherCategoryId implements Serializable {
    private static final long serialVersionUID = 7277822224441366925L;
    @Column(name = "category_category_id", nullable = false)
    private Integer categoryCategoryId;

    @Column(name = "teacher_teacher_id", nullable = false)
    private Integer teacherTeacherId;

    public Integer getCategoryCategoryId() {
        return categoryCategoryId;
    }

    public void setCategoryCategoryId(Integer categoryCategoryId) {
        this.categoryCategoryId = categoryCategoryId;
    }

    public Integer getTeacherTeacherId() {
        return teacherTeacherId;
    }

    public void setTeacherTeacherId(Integer teacherTeacherId) {
        this.teacherTeacherId = teacherTeacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TeacherCategoryId entity = (TeacherCategoryId) o;
        return Objects.equals(this.teacherTeacherId, entity.teacherTeacherId) &&
                Objects.equals(this.categoryCategoryId, entity.categoryCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherTeacherId, categoryCategoryId);
    }

}