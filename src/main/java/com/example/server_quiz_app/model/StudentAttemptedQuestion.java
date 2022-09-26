package com.example.server_quiz_app.model;

import javax.persistence.*;

@Entity
@Table(name = "student_attempted_question")
public class StudentAttemptedQuestion {
    @EmbeddedId
    private StudentAttemptedQuestionId id;

    @MapsId("studentStudentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_student_id", nullable = false)
    private Student studentStudent;

    @MapsId("questionQuestionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_question_id", nullable = false)
    private Question questionQuestion;

    @Column(name = "is_correct_attempt", nullable = false)
    private Byte isCorrectAttempt;

    public StudentAttemptedQuestionId getId() {
        return id;
    }

    public void setId(StudentAttemptedQuestionId id) {
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

    public Byte getIsCorrectAttempt() {
        return isCorrectAttempt;
    }

    public void setIsCorrectAttempt(Byte isCorrectAttempt) {
        this.isCorrectAttempt = isCorrectAttempt;
    }

}