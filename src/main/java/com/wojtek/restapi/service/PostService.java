package com.wojtek.restapi.service;

import com.wojtek.restapi.model.Post;
import com.wojtek.restapi.model.PostDTO;
import com.wojtek.restapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;
    public List<Post> getPosts(){
        return repository.findAllPosts(PageRequest.of(0,5));
    }

    public Post getPostById(long id){
        return repository.findById(id)
                .orElseThrow();
    }
}
