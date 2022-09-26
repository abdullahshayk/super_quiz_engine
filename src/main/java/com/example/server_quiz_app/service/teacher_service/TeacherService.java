package com.example.server_quiz_app.service.teacher_service;

import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.model.Teacher;
import com.example.server_quiz_app.request_models.UserCategoryReqBody;
import org.springframework.http.ResponseEntity;

public interface TeacherService {
    ResponseEntity<Response> getTeachers();

    ResponseEntity<Response> postTeacher(Teacher teacher);

    ResponseEntity<Response> addCategories(UserCategoryReqBody body);


}
