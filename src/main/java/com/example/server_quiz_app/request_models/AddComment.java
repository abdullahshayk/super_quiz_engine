package com.example.server_quiz_app.request_models;

import com.example.server_quiz_app.model.Comment;
import com.example.server_quiz_app.model.Post;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AddComment {
    Integer postId;
    Comment comment;
}
