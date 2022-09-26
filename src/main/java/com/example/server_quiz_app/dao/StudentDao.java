package com.example.server_quiz_app.dao;

import com.example.server_quiz_app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student,Integer> {

    Student findStudentByUsername(String username);


}
