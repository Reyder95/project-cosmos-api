package com.projectcosmos.api.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectcosmos.api.entity.User;
import com.projectcosmos.api.repository.UserRepository;
import com.projectcosmos.api.config.Helpers;
import com.projectcosmos.api.dto.User.UserLoginDto;
import com.projectcosmos.api.dto.User.UserResponseDto;
import com.projectcosmos.api.dto.User.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final Helpers helpers;

    @PostMapping("/logintest")
    public ResponseEntity<Map<String, Object>> loginTest(@RequestBody UserLoginDto loginDto) {
        User user = userRepository.findAll().stream()
            .filter(u -> u.getUsername().equals(loginDto.getUsername()))
            .findFirst().orElse(null);

        if (user == null) {
            return helpers.createResponseEntity(false, null, "User not found", HttpStatus.NOT_FOUND);
        }
        boolean matches = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());

        if (!matches) {
            return helpers.createResponseEntity(false, null, "Incorrect password", HttpStatus.UNAUTHORIZED);
        }

        return helpers.createResponseEntity(true, modelMapper.map(user, UserResponseDto.class), "Login successful", HttpStatus.OK);
    }
    
    @GetMapping("/basic")
    public ResponseEntity<Map<String, Object>> getAllBasic() {
        List<UserResponseDto> users = userRepository.findAll().stream()
            .map(user -> modelMapper.map(user, UserResponseDto.class))
            .toList();
        
            return helpers.createResponseEntity(true, users, null, HttpStatus.OK);
    }

    @GetMapping("/basic/{id}")
    public ResponseEntity<Map<String, Object>> getOneBasic(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null)
            return helpers.createResponseEntity(false, null, "User not found", HttpStatus.NOT_FOUND);

        return helpers.createResponseEntity(true, modelMapper.map(user, UserResponseDto.class), "User found", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll()
    {
        List<User> users = userRepository.findAll();
        return helpers.createResponseEntity(true, users, null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getOne(@PathVariable Integer id)
    {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return helpers.createResponseEntity(false, null, "User not found", HttpStatus.NOT_FOUND);
        }

        return helpers.createResponseEntity(true, user, "User found", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User newUser)
    {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User saved = userRepository.save(newUser);
        return helpers.createResponseEntity(true, modelMapper.map(saved, UserResponseDto.class), "User created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Integer id, @RequestBody UserUpdateDto updatedUser)
    {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return helpers.createResponseEntity(false, null, "User not found", HttpStatus.NOT_FOUND);
        }
        User savedUser = userRepository.save(user);
        return helpers.createResponseEntity(true, modelMapper.map(savedUser, UserResponseDto.class), "User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Integer id)
    {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return helpers.createResponseEntity(false, null, "User not found", HttpStatus.NOT_FOUND);
        }

        userRepository.deleteById(id);
        return helpers.createResponseEntity(true, user, "User with ID " + id + " deleted successfully", HttpStatus.OK);
    }
}
