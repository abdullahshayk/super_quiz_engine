package com.example.server_quiz_app.model;

import javax.persistence.*;

@Entity
@Table(name = "student_like_post")
public class StudentLikePost {
    @EmbeddedId
    private StudentLikePostId id;

    @MapsId("studentStudentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_student_id", nullable = false)
    private Student studentStudent;

    @MapsId("postPostId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_post_id", nullable = false)
    private Post postPost;

    public StudentLikePostId getId() {
        return id;
    }

    public void setId(StudentLikePostId id) {
        this.id = id;
    }

    public Student getStudentStudent() {
        return studentStudent;
    }

    public void setStudentStudent(Student studentStudent) {
        this.studentStudent = studentStudent;
    }

    public Post getPostPost() {
        return postPost;
    }

    public void setPostPost(Post postPost) {
        this.postPost = postPost;
    }

}