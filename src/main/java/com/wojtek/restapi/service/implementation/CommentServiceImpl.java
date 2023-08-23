package com.wojtek.restapi.service.implementation;

import com.wojtek.restapi.model.Comment;
import com.wojtek.restapi.repository.CommentRepository;
import com.wojtek.restapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    @Override
    public List<Comment> getComments(int page, Sort.Direction sort) {
        return commentRepository.findAllComments(PageRequest.of(
                page,
                20,
                Sort.by(sort,"id")
        ));
    }

    @Override
    public Comment getCommentById(long id) {
        return commentRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Comment> getCommentsByUserId(int page, Sort.Direction sort, Long id) {
        return commentRepository.findAllByUserId(PageRequest.of(
                page,
                20,
                Sort.by(sort,"id")
        ), id);
    }

    @Override
    public List<Comment> getCommentsByPostId(int page, Sort.Direction sort, Long id) {
        return commentRepository.findAllByPostId(PageRequest.of(page,20,sort),id);
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment editComment(Comment comment) {
        Comment edittedComment = commentRepository.findById(comment.getId()).orElseThrow();
        edittedComment.setContent(comment.getContent());
        return edittedComment;
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
