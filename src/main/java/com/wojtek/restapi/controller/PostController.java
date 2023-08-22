package com.wojtek.restapi.controller;

import com.wojtek.restapi.dao.request.PostRequest;
import com.wojtek.restapi.model.Post;
import com.wojtek.restapi.model.PostDTO;
import com.wojtek.restapi.service.implementation.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl service;

    @GetMapping("/posts")
    public List<PostDTO> getPosts(@RequestParam(required = false) Integer page,@RequestParam(required = false, defaultValue = "ASC") Sort.Direction sort){
        int pageNumber = (page != null && page >= 0) ? page : 0;
        return PostDTOMapper.mapToPostDTOS(service.getPosts(pageNumber, sort));
    }

    @GetMapping("/posts/comments")
    public List<Post> getPostsWithComments(@RequestParam(required = false) Integer page,@RequestParam(required = false, defaultValue = "ASC") Sort.Direction sort){
        int pageNumber = (page != null && page >= 0) ? page : 0;
        return service.getPostsWithComments(pageNumber, sort);
    }

    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable long id){
        return service.getPostById(id);
    }

    @GetMapping("/posts/user/{id}")
    public List<PostDTO> getAllPostByUser(@PathVariable long id,
                                 @RequestParam(required = false) Integer page,
                                 @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sort){
        int pageNumber = (page != null && page >= 0) ? page : 0;
        return PostDTOMapper.mapToPostDTOS(service.getPostsByUserId(pageNumber,sort,id));
    }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post){
        return service.addPost(post);
    }

    @PutMapping("/posts")
    public Post editPost(@RequestBody Post post){
        return service.editPost(post);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable Long id){
        service.deletePost(id);
    }
}
