package com.example.server_quiz_app.dao;

import com.example.server_quiz_app.model.StudentAttemptedQuestion;
import com.example.server_quiz_app.model.StudentAttemptedQuestionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAttemptQuestionDao extends JpaRepository<StudentAttemptedQuestion, StudentAttemptedQuestionId> {

}
