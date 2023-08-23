package com.wojtek.restapi.controller;

import com.wojtek.restapi.model.Comment;
import com.wojtek.restapi.model.CommentDTO;
import com.wojtek.restapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    @GetMapping("/")
    public List<Comment> getAllComments(@RequestParam(required = false) Integer page, @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sort){
        int pageNumber = (page != null && page >= 0) ? page : 0;
        return commentService.getComments(pageNumber,sort);
    }

    @PostMapping("/")
    public Comment addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
}
