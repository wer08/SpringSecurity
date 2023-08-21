package com.wojtek.restapi.service;

import com.wojtek.restapi.model.Comment;
import com.wojtek.restapi.model.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.List;

public interface PostService {
    List<Post> getPosts(int page, Sort.Direction sort);

    Post getPostById(long id);

    List<Post> getPostsWithComments(int page, Sort.Direction sort);

    Post addPost(Post post);

    Post editPost(Post post);

    void deletePost(Long id);
}
