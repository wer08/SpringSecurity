package com.wojtek.restapi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "_user_id")
    private long userId;
    private String title;
    private String content;
    private LocalDateTime created;

    @OneToMany(cascade = REMOVE)
    @JoinColumn(name = "postId", updatable = false, insertable = false)
    private List<Comment> comment;
}
