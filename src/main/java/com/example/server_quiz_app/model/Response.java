package com.example.server_quiz_app.model;


import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private Boolean isSuccessful;
    private String message;
    private Object data;

}
