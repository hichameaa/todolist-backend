package com.hichamea.todolist.service.impl;

import com.hichamea.todolist.dto.UserDto;
import com.hichamea.todolist.exception.InvalidEntityException;
import com.hichamea.todolist.repository.UserRepository;
import com.hichamea.todolist.service.AuthenticationService;
import com.hichamea.todolist.exception.EntityNotFoundException;
import com.hichamea.todolist.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for user authentication.
 * This class provides the implementation for the authentication of users based on their credentials.
 */
@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    /**
     * Constructs an instance of AuthenticationServiceImpl with the provided UserRepository.
     *
     * @param userRepository The UserRepository used for user authentication.
     */
    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authenticate a user based on the provided UserDto.
     *
     * @param user The UserDto containing user credentials.
     * @return A UserDto representing the authenticated user.
     * @throws InvalidEntityException  If the provided UserDto is null.
     * @throws EntityNotFoundException If user authentication fails due to incorrect credentials.
     */
    @Override
    public UserDto authenticate(UserDto user) {
        List<String> validationMessages = UserValidator.validateUserAuthenticationFields(user);

        if (!validationMessages.isEmpty()) {
            log.error("Authentication failed due to validation errors: {}", validationMessages);
            throw new InvalidEntityException("User validation failed");
        }
        return userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword())
                             .map(UserDto::fromEntity)
                             .orElseThrow(() -> new EntityNotFoundException("Login error: Incorrect credentials."));
    }
}
