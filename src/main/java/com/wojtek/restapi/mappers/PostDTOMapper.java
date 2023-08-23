package com.wojtek.restapi.mappers;

import com.wojtek.restapi.model.Post;
import com.wojtek.restapi.model.PostDTO;

import java.util.List;

public class PostDTOMapper {

    private PostDTOMapper() {
    }

    public static List<PostDTO> mapToPostDTOS(List<Post> posts) {
        return posts.stream()
                .map(PostDTOMapper::mapToPostDTO)
                .toList();
    }

    private static PostDTO mapToPostDTO(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .created(post.getCreated())
                .build();
    }
}
