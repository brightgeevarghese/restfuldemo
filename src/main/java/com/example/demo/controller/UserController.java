package com.example.demo.controller;

import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.response.UserResponseDto;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        Optional<UserResponseDto> userResponseDto = userService.addUser(userRequestDto);
        if (userResponseDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> userResponseDtos = userService.findAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDtos);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable String username) throws UserNotFoundException {
        System.out.println("path variable");
        Optional<UserResponseDto> userResponseDto = userService.findUserByName(username);
        if (userResponseDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userResponseDto.get());
        }
        throw new UserNotFoundException(username + " not found");
    }

    @GetMapping("/username")
    public ResponseEntity<UserResponseDto> getUserByUsername(@RequestParam String username) throws UserNotFoundException {
        System.out.println("query parameter");
        Optional<UserResponseDto> userResponseDto = userService.findUserByName(username);
        if (userResponseDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userResponseDto.get());
        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        throw new UserNotFoundException(username + " is not found");
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable String username, @Valid @RequestBody UserRequestDto userRequestDto) throws UserNotFoundException {
        Optional<UserResponseDto> userResponseDto = userService.updateUser(username, userRequestDto);
        if (userResponseDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userResponseDto.get());
        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        throw new UserNotFoundException(username + " is not found to edit.");
    }

    @PatchMapping("/{username}")
    public ResponseEntity<UserResponseDto> updateUserPartially(@PathVariable String username, @Valid @RequestBody UserRequestDto userRequestDto) throws UserNotFoundException {
        Optional<UserResponseDto> userResponseDto = userService.updateUserPartially(username, userRequestDto);
        if (userResponseDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userResponseDto.get());
        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();//Resource is not found
        throw new UserNotFoundException(username + " is not found to edit.");
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) throws UserNotFoundException {
        userService.deleteUserByName(username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}