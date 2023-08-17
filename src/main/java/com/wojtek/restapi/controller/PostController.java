package com.wojtek.restapi.controller;

import com.wojtek.restapi.model.Post;
import com.wojtek.restapi.model.PostDTO;
import com.wojtek.restapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @GetMapping("/posts")
    public List<PostDTO> getPosts(){
        return PostDTOMapper.mapToPostDTOS(service.getPosts());
    }




    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable long id){
        return service.getPostById(id);
    }
}
