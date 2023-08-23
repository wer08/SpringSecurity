package com.wojtek.restapi.service;

import com.wojtek.restapi.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();
    List<User> getAllUsers(int page);

    User getUserById(long id);
    void deleteUserByUsername(String token);
}
