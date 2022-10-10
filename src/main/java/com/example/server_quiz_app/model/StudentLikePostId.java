package com.example.server_quiz_app.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentLikePostId implements Serializable {
    private static final long serialVersionUID = -3745514350117245759L;
    @Column(name = "student_student_id", nullable = false)
    private Integer studentStudentId;

    @Column(name = "post_post_id", nullable = false)
    private Integer postPostId;

    public Integer getStudentStudentId() {
        return studentStudentId;
    }

    public void setStudentStudentId(Integer studentStudentId) {
        this.studentStudentId = studentStudentId;
    }

    public Integer getPostPostId() {
        return postPostId;
    }

    public void setPostPostId(Integer postPostId) {
        this.postPostId = postPostId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentLikePostId entity = (StudentLikePostId) o;
        return Objects.equals(this.studentStudentId, entity.studentStudentId) &&
                Objects.equals(this.postPostId, entity.postPostId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentStudentId, postPostId);
    }

}