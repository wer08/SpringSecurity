package com.wojtek.restapi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long postId;
    @Column(name = "_user_id")
    private long userId;
    private String content;
    private LocalDateTime created;


}
