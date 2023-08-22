package com.wojtek.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wojtek.restapi.RestapiApplication;
import com.wojtek.restapi.model.Post;
import com.wojtek.restapi.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional
    void shouldGetSinglePost() throws Exception {
        //given
        Post newPost= new Post();
        newPost.setTitle("Test");
        newPost.setContent("Test content");
        newPost.setCreated(LocalDateTime.now());
        postRepository.save(newPost);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/posts/" + newPost.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        //then
        Post post = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Post.class);
        assertNotNull(post);
        assertEquals(post.getId(),newPost.getId());
        assertEquals(post.getTitle(),newPost.getTitle());
        assertEquals(post.getContent(),newPost.getContent());

    }
}