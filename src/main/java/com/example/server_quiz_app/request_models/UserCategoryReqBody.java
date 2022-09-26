package com.example.server_quiz_app.request_models;

import com.example.server_quiz_app.model.Category;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UserCategoryReqBody {
    String username;
    List<Category> categoryList;
}