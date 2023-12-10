package com.hichamea.todolist.service;

import com.hichamea.todolist.dto.UserDto;

import java.util.List;

/**
 * Service interface for managing user-related operations.
 * This interface defines methods for managing and interacting with user entities and their operations.
 */
public interface UserService {

    List<UserDto> findAllUsers();

    UserDto findUserById(Long userId);

    /**
     * Saves a user using the provided UserDto.
     *
     * @param user The UserDto to be saved.
     * @return The saved UserDto.
     */
    UserDto saveUser(UserDto user);

    UserDto updateUser(Long userId, UserDto user);

    void deleteUserById(Long userId);
}
