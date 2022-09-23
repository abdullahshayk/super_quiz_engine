package com.example.server_quiz_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class GetQuestionByCategoryAndTypeRequest {
    private List<Integer> categorys;
    private List<Integer> types;

}
