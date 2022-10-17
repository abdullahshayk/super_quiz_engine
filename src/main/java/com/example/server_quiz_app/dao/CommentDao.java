package com.example.server_quiz_app.dao;

import com.example.server_quiz_app.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment,Integer> {
}
