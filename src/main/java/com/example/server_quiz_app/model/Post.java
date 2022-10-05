package com.example.server_quiz_app.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "`post`")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Integer id;

    @Column(name = "`post_content`", nullable = true)
    private String postContent;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments=new ArrayList<>();

    @ManyToMany(mappedBy = "likedPosts")
    @JsonIgnore
    private List<Student> likedBy = new ArrayList<>();
}
