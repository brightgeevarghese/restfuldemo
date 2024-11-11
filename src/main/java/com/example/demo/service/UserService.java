package com.example.demo.service;

import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.response.UserResponseDto;
import com.example.demo.exception.user.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    //CRUD
    Optional<UserResponseDto> addUser(UserRequestDto userRequestDto);
    Optional<UserResponseDto> updateUserPartially(String username, UserRequestDto userRequestDto) throws UserNotFoundException;
    Optional<UserResponseDto> updateUser(String username, UserRequestDto userRequestDto) throws UserNotFoundException;
    Optional<UserResponseDto> findUserByName(String username) throws UserNotFoundException;
    List<UserResponseDto> findAllUsers();
    void deleteUserByName(String username) throws UserNotFoundException;
}
