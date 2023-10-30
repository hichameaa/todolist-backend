package com.hichamea.todolist.service.impl;

import com.hichamea.todolist.dto.UserDto;
import com.hichamea.todolist.exception.InvalidEntityException;
import com.hichamea.todolist.repository.UserRepository;
import com.hichamea.todolist.service.AuthenticationService;
import com.hichamea.todolist.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto authenticate(UserDto user) {
        if (user == null) {
            throw new InvalidEntityException("User object is null");
        }
        return userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword())
                             .map(UserDto::fromEntity)
                             .orElseThrow(() -> new EntityNotFoundException("Login error: Incorrect credentials."));
    }
}
