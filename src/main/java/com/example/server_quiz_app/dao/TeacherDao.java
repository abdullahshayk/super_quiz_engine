package com.example.server_quiz_app.dao;

import com.example.server_quiz_app.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherDao extends JpaRepository<Teacher,Integer>{

}
