package com.wojtek.restapi.service;

import com.wojtek.restapi.dao.request.SignUpRequest;
import com.wojtek.restapi.dao.request.SigninRequest;
import com.wojtek.restapi.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

}
