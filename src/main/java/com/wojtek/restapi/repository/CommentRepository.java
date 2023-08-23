package com.wojtek.restapi.repository;

import com.wojtek.restapi.model.Comment;
import com.wojtek.restapi.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("Select c from Comment c")
    List<Comment> findAllComments(Pageable page);
    @Query("SELECT c FROM Comment c WHERE c._userId = :userId")
    List<Comment> findAllByUserId( Pageable page,@Param("userId") Long userId);

    @Query("SELECT c FROM Comment c WHERE c.postId = :postId")
    List<Comment> findAllByPostId( Pageable page,@Param("postId") Long postId);

    List<Comment> findAllByPostIdIn(List<Long> ids);
}
