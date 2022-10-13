package com.example.server_quiz_app.exception_handling;

import com.example.server_quiz_app.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Response> alreadyExistsException(AlreadyExistsException alreadyExistsException){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new Response(false,alreadyExistsException.getMessage(),null));
    }




    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> resourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Response(false,resourceNotFoundException.getMessage(),null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> exception(Exception e){
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Response(false,"Server error",null));
    }


}
