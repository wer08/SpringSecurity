package com.wojtek.restapi.service;

import com.wojtek.restapi.model.Comment;
import com.wojtek.restapi.model.Post;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CommentService {
    List<Comment> getComments(int page, Sort.Direction sort);

    Comment getCommentById(long id);

    List<Comment> getCommentsByUserId(int page, Sort.Direction sort,Long id);
    List<Comment> getCommentsByPostId(int page, Sort.Direction sort,Long id);
    Comment addComment(Comment comment);

    Comment editComment(Comment comment);

    void deleteComment(Long id);
}
