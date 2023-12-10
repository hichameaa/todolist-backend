package com.hichamea.todolist.service.impl;


import com.hichamea.todolist.dto.UserDto;
import com.hichamea.todolist.exception.EntityNotFoundException;
import com.hichamea.todolist.exception.InvalidEntityException;
import com.hichamea.todolist.model.User;
import com.hichamea.todolist.repository.UserRepository;
import com.hichamea.todolist.service.UserService;
import com.hichamea.todolist.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service implementation for managing user-related operations.
 * This class provides the implementation for managing user-related operations.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findAllUsers() {
        log.info("Fetching all users from the database.");
        List<UserDto> users = userRepository.findAll().stream()
                                            .map(UserDto::fromEntity)
                                            .toList();
        log.info("Found {} users.", users.size());
        return users;
    }

    @Override
    public UserDto findUserById(Long userId) {
        log.info("Fetching user with ID: {}", userId);
        User userFound = userRepository.findById(userId)
                                       .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        log.info("User found with ID: {}", userId);
        return UserDto.fromEntity(userFound);
    }

    public UserDto saveUser(UserDto user) {
        List<String> validationErrors = UserValidator.validateUserFields(user);

        if (!validationErrors.isEmpty()) {
            log.error("User validation failed for {} with errors: {}", user, validationErrors);
            throw new InvalidEntityException("User validation failed");
        }

        UserDto savedUser = UserDto.fromEntity(userRepository.save(UserDto.toEntity(user)));
        log.info("User successfully created for user ID {}", savedUser.getId());
        return savedUser;
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updatedUserDto) {
        List<String> validationMessages = UserValidator.validateUserFields(updatedUserDto);

        if (!validationMessages.isEmpty()) {
            log.error("Failed to update user with ID {} due to validation errors: {}", userId, validationMessages);
            throw new InvalidEntityException("User validation failed");
        }

        User userToUpdate = userRepository.findById(userId)
                                          .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        updateUserData(userToUpdate, updatedUserDto);

        UserDto updatedUser = UserDto.fromEntity(userRepository.save(userToUpdate));
        log.info("User with ID {} successfully updated.", updatedUser.getId());
        return updatedUser;
    }

    @Override
    public void deleteUserById(Long userId) {
        log.warn("Attempting to delete user with ID: {}", userId);

        if (!userRepository.existsById(userId)) {
            log.warn("User with ID {} not found. Deletion skipped.", userId);
            return;
        }

        userRepository.deleteById(userId);
        log.info("User with ID {} successfully deleted.", userId);
    }

    private void updateUserData(User userToUpdate, UserDto updatedUserDto) {
        userToUpdate.setFirstName(updatedUserDto.getFirstName());
        userToUpdate.setLastName(updatedUserDto.getLastName());
        userToUpdate.setUserName(updatedUserDto.getUserName());
        userToUpdate.setEmail(updatedUserDto.getEmail());
        userToUpdate.setPassword(updatedUserDto.getPassword());
    }
}
