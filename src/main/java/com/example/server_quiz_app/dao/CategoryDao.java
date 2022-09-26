package com.example.server_quiz_app.dao;

import com.example.server_quiz_app.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category,Integer>{
    @Query(
            nativeQuery = true,
            value = "SELECT q.* from category q join student_category qt ON q.category_id=qt.category_category_id join student c ON qt.student_student_id=c.student_id WHERE c.student_id=:id"
    )
    List<Category> getCategoriesOfStudent(@Param("id") Integer id);

    @Query(
            nativeQuery = true,
            value = "SELECT c.* from category c join teacher_category ct ON c.category_id=ct.category_category_id join teacher t ON ct.teacher_teacher_id=t.teacher_id WHERE t.teacher_id=:id"
    )
    List<Category> getCategoriesOfTeacher(@Param("id") Integer id);
}




