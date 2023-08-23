package com.wojtek.restapi.mappers;

import com.wojtek.restapi.model.Comment;
import com.wojtek.restapi.model.CommentDTO;
import com.wojtek.restapi.model.Post;
import com.wojtek.restapi.model.PostDTO;

import java.util.List;

public class CommentDTOMapper {
    private CommentDTOMapper() {
    }

    public static List<CommentDTO> mapToCommentsDTOS(List<Comment> comments) {
        return comments.stream()
                .map(CommentDTOMapper::mapToCommentDTO)
                .toList();
    }

    private static CommentDTO mapToCommentDTO(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .created(comment.getCreated())
                .build();
    }
}
