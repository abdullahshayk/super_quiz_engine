package com.example.server_quiz_app.dao;
import com.example.server_quiz_app.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionDao extends JpaRepository<Question,Integer>{

}
