package com.wojtek.restapi.service.implementation;

import com.wojtek.restapi.model.User;
import com.wojtek.restapi.repository.UserRepository;
import com.wojtek.restapi.service.JwtService;
import com.wojtek.restapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private static final int PAGE_SIZE = 20;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public List<User> getAllUsers(int page) {
        return userRepository.findAllUsers(PageRequest.of(page, PAGE_SIZE));
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void deleteUserByUsername(String fullToken) {
        String token =fullToken.substring(6);
        String username = jwtService.extractUserName(token);
        userRepository.deleteByEmail(username);
    }
}
