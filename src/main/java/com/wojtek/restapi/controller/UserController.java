package com.wojtek.restapi.controller;

import com.wojtek.restapi.mappers.UserDTOMapper;
import com.wojtek.restapi.model.User;
import com.wojtek.restapi.model.UserDto;
import com.wojtek.restapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/")
    public List<UserDto> getAllUsers(@RequestParam(required = false) Integer page){
        int pageNumber = (page != null && page >= 0) ? page : 0;
        return UserDTOMapper.mapUsersToDto(userService.getAllUsers(pageNumber));
    }
    @GetMapping("/lazy")
    public List<User> getAllUsersLazy(@RequestParam(required = false) Integer page){
        int pageNumber = (page != null && page >= 0) ? page : 0;
        return userService.getAllUsers(pageNumber);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable long id){
        return UserDTOMapper.mapUserToDto(userService.getUserById(id));
    }

    @DeleteMapping("/")
    public void deleteUser(@RequestHeader(name = AUTHORIZATION) String token){
        userService.deleteUserByUsername(token);
    }
}
