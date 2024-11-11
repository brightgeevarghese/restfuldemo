package com.example.demo.mapper;


import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.response.UserResponseDto;
import com.example.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userRequestDtoToUser(UserRequestDto userRequestDto);
    @Mapping(source = "username", target="username")
    UserResponseDto userToUserResponseDto(User user);
    List<UserResponseDto> userListToUserResponseDtoList(List<User> userList);
}
