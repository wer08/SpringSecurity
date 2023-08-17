package com.wojtek.restapi.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostDTO {
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
}
