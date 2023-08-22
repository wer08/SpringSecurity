package com.wojtek.restapi.service.implementation;

import com.wojtek.restapi.model.Post;
import com.wojtek.restapi.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WithMockUser
class PostServiceImplTest {

    @Autowired
    private PostService postService;
    @Test
    void shouldGetPostById() {
        //given
        //when
        Post postById = postService.getPostById(1L);
        //then
        assertNotNull(postById);
        assertEquals(postById.getId(),1L);
    }
}