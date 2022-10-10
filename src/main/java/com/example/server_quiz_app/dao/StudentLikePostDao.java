package com.example.server_quiz_app.dao;

import com.example.server_quiz_app.model.StudentLikePost;
import com.example.server_quiz_app.model.StudentLikePostId;
import com.example.server_quiz_app.model.StudentLikeQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentLikePostDao extends JpaRepository<StudentLikePost, StudentLikePostId> {

    List<StudentLikePost> findByPostPostId(Integer postId);


}
