package com.example.server_quiz_app.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentLikeQuestionId implements Serializable {
    private static final long serialVersionUID = -6599710718403868370L;
    @Column(name = "student_student_id", nullable = false)
    private Integer studentStudentId;

    @Column(name = "question_question_id", nullable = false)
    private Integer questionQuestionId;

    public Integer getStudentStudentId() {
        return studentStudentId;
    }

    public void setStudentStudentId(Integer studentStudentId) {
        this.studentStudentId = studentStudentId;
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
        StudentLikeQuestionId entity = (StudentLikeQuestionId) o;
        return Objects.equals(this.studentStudentId, entity.studentStudentId) &&
                Objects.equals(this.questionQuestionId, entity.questionQuestionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentStudentId, questionQuestionId);
    }

}