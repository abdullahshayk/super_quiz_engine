package com.example.server_quiz_app.request_models;

import com.example.server_quiz_app.model.Category;
import com.example.server_quiz_app.model.Teacher;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class FollowTeacher {
    Integer studentId;
    Integer teacherId;
}