package com.wojtek.restapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wojtek.restapi.dao.request.SignUpRequest;
import com.wojtek.restapi.dao.request.SigninRequest;
import com.wojtek.restapi.dao.response.JwtAuthenticationResponse;
import com.wojtek.restapi.model.Role;
import com.wojtek.restapi.model.User;
import com.wojtek.restapi.repository.UserRepository;
import org.assertj.core.error.ShouldNotBeEqualIgnoringWhitespace;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;


    private static SignUpRequest signUpRequest;
    private static SigninRequest signinRequest;

    @BeforeAll
    static void setUp() throws JsonProcessingException {
        String test = "test";
        signUpRequest = SignUpRequest.builder()
                .email(test)
                .firstName(test)
                .lastName(test)
                .password(test)
                .build();
        signinRequest = SigninRequest.builder()
                .email(test)
                .password(test)
                .build();

    }

    @Test
    @Transactional
    void shouldSignup() throws Exception {
        //given
        String json = objectMapper.writeValueAsString(signUpRequest);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/signup")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        //then
        String token = mvcResult.getResponse().getContentAsString();
    }

    @Test
    @Transactional
    void shouldSignin() throws Exception {
        //given

        String json = objectMapper.writeValueAsString(signinRequest);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/signin")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        //then
        String token = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), JwtAuthenticationResponse.class).getToken();

        MvcResult mvcResult2 = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/resource")
                        .header("Authorization","Bearer "+token))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        assertEquals(mvcResult2.getResponse().getContentAsString(),"Here is your resource");
    }

}