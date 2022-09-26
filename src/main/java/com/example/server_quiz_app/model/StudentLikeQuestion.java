package com.example.server_quiz_app.model;

import javax.persistence.*;

@Entity
@Table(name = "student_like_question")
public class StudentLikeQuestion {
    @EmbeddedId
    private StudentLikeQuestionId id;

    @MapsId("studentStudentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_student_id", nullable = false)
    private Student studentStudent;

    @MapsId("questionQuestionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_question_id", nullable = false)
    private Question questionQuestion;

    public StudentLikeQuestionId getId() {
        return id;
    }

    public void setId(StudentLikeQuestionId id) {
        this.id = id;
    }

    public Student getStudentStudent() {
        return studentStudent;
    }

    public void setStudentStudent(Student studentStudent) {
        this.studentStudent = studentStudent;
    }

    public Question getQuestionQuestion() {
        return questionQuestion;
    }

    public void setQuestionQuestion(Question questionQuestion) {
        this.questionQuestion = questionQuestion;
    }

}