package com.example.server_quiz_app.service.option_service;

import com.example.server_quiz_app.model.Option;
import com.example.server_quiz_app.model.Response;
import org.springframework.http.ResponseEntity;

public interface OptionService {
    ResponseEntity<Response> getOptions();

    ResponseEntity<Response> saveOption(Option option);
}

