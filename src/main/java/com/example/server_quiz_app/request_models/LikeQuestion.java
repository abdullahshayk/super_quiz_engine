package com.example.server_quiz_app.request_models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class LikeQuestion {
    Integer studentId;
    Integer questionId;
}