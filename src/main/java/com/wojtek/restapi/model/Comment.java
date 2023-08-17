package com.wojtek.restapi.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {
    @Id
    private long id;
    private String content;
    private LocalDateTime created;


}
