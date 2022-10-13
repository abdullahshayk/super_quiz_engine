package com.example.server_quiz_app.request_models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AttemptQuestion {
    Integer questionId;
    Boolean isCorrect;
}
