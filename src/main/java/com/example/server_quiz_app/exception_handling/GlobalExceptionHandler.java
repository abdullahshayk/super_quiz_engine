package com.example.server_quiz_app.exception_handling;

import com.example.server_quiz_app.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> resourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Response(false,resourceNotFoundException.getMessage(),null));
    }


}
