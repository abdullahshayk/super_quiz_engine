package com.example.server_quiz_app.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class StudentCategoryReqBody {
    String username;
    List<Category> categoryList;
}