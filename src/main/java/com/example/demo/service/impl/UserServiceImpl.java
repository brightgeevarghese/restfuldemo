package com.example.demo.service.impl;

import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.response.UserResponseDto;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public Optional<UserResponseDto> addUser(UserRequestDto userRequestDto) {
        User user = userMapper.userRequestDtoToUser(userRequestDto);
        User savedUser = userRepository.save(user);
        System.out.println(savedUser + " is saved.");
        UserResponseDto userResponseDto = userMapper.userToUserResponseDto(savedUser);
        System.out.println(userResponseDto + " in service");
        return Optional.of(userResponseDto);
    }

    @Override
    public Optional<UserResponseDto> updateUserPartially(String username, UserRequestDto userRequestDto) throws UserNotFoundException {
        Optional<User> foundUser = userRepository.findByUsername(username);
        if (foundUser.isPresent()) {
            if (userRequestDto.username() != null) {
                foundUser.get().setUsername(userRequestDto.username());
            }
            if (userRequestDto.password() != null) {
                foundUser.get().setPassword(userRequestDto.password());
            }
            User modifiedUser = userRepository.save(foundUser.get());
            return Optional.of(userMapper.userToUserResponseDto(modifiedUser));
        } else {
            throw new UserNotFoundException(username + " not found");
        }
    }

    @Override
    public Optional<UserResponseDto> updateUser(String username, UserRequestDto userRequestDto) throws UserNotFoundException {
        Optional<User> foundUser = userRepository.findByUsername(username);
        if (foundUser.isPresent()) {
            foundUser.get().setUsername(userRequestDto.username());
            foundUser.get().setPassword(userRequestDto.password());
            User modifiedUser = userRepository.save(foundUser.get());
            return Optional.of(userMapper.userToUserResponseDto(modifiedUser));
        } else {
            throw new UserNotFoundException(username + " not found");
        }
    }

    @Override
    public Optional<UserResponseDto> findUserByName(String username) throws UserNotFoundException{
        Optional<User> foundUser = userRepository.findByUsername(username);
        if (foundUser.isPresent()) {
            UserResponseDto userResponseDto = new UserResponseDto(foundUser.get().getUsername());
            return Optional.of(userResponseDto);
        } else {
            throw new UserNotFoundException(username + " not found");
        }
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.userListToUserResponseDtoList(users);
    }

    @Override
    public void deleteUserByName(String username) throws UserNotFoundException{
        Optional<User> foundUser = userRepository.findByUsername(username);
        if (foundUser.isPresent()) {
            userRepository.delete(foundUser.get());
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}