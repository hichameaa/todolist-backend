package com.hichamea.todolist.service;

import com.hichamea.todolist.dto.UserDto;

/**
 * Service interface for user authentication.
 * This interface defines a method for authenticating a user and returning a UserDto.
 */
public interface AuthenticationService {

    /**
     * Authenticate a user based on the provided UserDto.
     *
     * @param user The UserDto containing user credentials.
     * @return A UserDto representing the authenticated user.
     */
    UserDto authenticate(UserDto user);
}
