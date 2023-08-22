package com.wojtek.restapi.service.implementation;

import com.wojtek.restapi.model.Comment;
import com.wojtek.restapi.model.Post;
import com.wojtek.restapi.repository.CommentRepository;
import com.wojtek.restapi.repository.PostRepository;
import com.wojtek.restapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    public List<Post> getPosts(int page, Sort.Direction sort){
        return postRepository.findAllPosts(PageRequest.of(
                page,
                PAGE_SIZE,
                Sort.by(sort,"id")));
    }

    @Override
    public List<Post> getPostsByUserId(int page, Sort.Direction sort,Long id) {
        return postRepository.findAllByUserId(PageRequest.of(
                page,
                PAGE_SIZE,
                Sort.by(sort,"id")),id);
    }

    @Cacheable(cacheNames = "SinglePost", key = "#id")
    public Post getPostById(long id){
        return postRepository.findById(id)
                .orElseThrow();
    }

    @Cacheable(cacheNames = "PostsWithComments")
    public List<Post> getPostsWithComments(int page, Sort.Direction sort) {
        List<Post> allPosts = postRepository
                .findAllPosts(PageRequest.of(
                        page,
                        PAGE_SIZE,
                        Sort.by(sort,"id")));

        List<Long> ids = allPosts.stream()
                .map(Post::getId)
                .toList();

        List<Comment> comments = commentRepository.findAllByPostIdIn(ids);
        allPosts.forEach(post -> post.setComment(extractComments(comments, post.getId())));

        return allPosts;

    }

    private List<Comment> extractComments(List<Comment> comments, long id) {
        return comments.stream()
                .filter(comment -> comment.getPostId() == id)
                .toList();
    }

    @CacheEvict(cacheNames = "PostsWithComments", allEntries = true)
    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    @CachePut(cacheNames = "SinglePost", key = "#result.id")
    public Post editPost(Post post) {
        Post editedPost = postRepository.findById(post.getId()).orElseThrow();
        editedPost.setTitle(post.getTitle());
        editedPost.setContent(post.getContent());
        return editedPost;
    }

    @CacheEvict(cacheNames = "SinglePost")
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
