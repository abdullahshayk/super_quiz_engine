package com.example.server_quiz_app.dao;

import com.example.server_quiz_app.model.StudentLikeQuestion;
import com.example.server_quiz_app.model.StudentLikeQuestionId;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentLikesQuestionDao extends JpaRepository<StudentLikeQuestion, StudentLikeQuestionId> {

    List<StudentLikeQuestion> findByQuestionQuestionId(Integer questionId);
}
