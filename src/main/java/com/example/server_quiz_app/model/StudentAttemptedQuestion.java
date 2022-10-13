package com.example.server_quiz_app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "student_attempted_question")
public class StudentAttemptedQuestion {
    @EmbeddedId
    private StudentAttemptedQuestionId id=new StudentAttemptedQuestionId();

    @MapsId("studentStudentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_student_id", nullable = false)
    private Student studentStudent;

    @MapsId("questionQuestionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_question_id", nullable = false)
    private Question questionQuestion;

    @Column(name = "is_correct_attempt", nullable = false)
    private Boolean isCorrectAttempt;


}