package com.example.server_quiz_app.request_models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class GetQuestionByCategoryAndType {
    private List<Integer> categorys;
    private List<Integer> types;

}
