package com.example.server_quiz_app.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentFollowTeacherId implements Serializable {
    private static final long serialVersionUID = -1541633739260185144L;
    @Column(name = "student_student_id", nullable = false)
    private Integer studentStudentId;

    @Column(name = "teacher_teacher_id", nullable = false)
    private Integer teacherTeacherId;

    public Integer getStudentStudentId() {
        return studentStudentId;
    }

    public void setStudentStudentId(Integer studentStudentId) {
        this.studentStudentId = studentStudentId;
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
        StudentFollowTeacherId entity = (StudentFollowTeacherId) o;
        return Objects.equals(this.studentStudentId, entity.studentStudentId) &&
                Objects.equals(this.teacherTeacherId, entity.teacherTeacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentStudentId, teacherTeacherId);
    }

}