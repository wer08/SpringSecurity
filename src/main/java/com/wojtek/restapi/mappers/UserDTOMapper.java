package com.wojtek.restapi.mappers;

import com.wojtek.restapi.model.User;
import com.wojtek.restapi.model.UserDto;

import java.util.List;

public class UserDTOMapper {
    private UserDTOMapper(){}
    public static UserDto mapUserToDto(User user){
        return UserDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .id(user.getId())
                .build();
    }
    public static List<UserDto> mapUsersToDto(List<User> users){
        return users.stream()
                .map(UserDTOMapper::mapUserToDto)
                .toList();
    }
}
