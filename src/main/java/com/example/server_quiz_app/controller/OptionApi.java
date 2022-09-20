package com.example.server_quiz_app.controller;

import com.example.server_quiz_app.model.Option;
import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OptionApi {
    @Autowired
    private OptionService optionService;

    @GetMapping("options")
    public ResponseEntity<Response> getOptions() {
        return optionService.getOptions();
    }

    @PostMapping("saveOption")
    public ResponseEntity<Response> saveQuestion(@RequestBody Option option){
        return optionService.saveOption(option);
    }
}
