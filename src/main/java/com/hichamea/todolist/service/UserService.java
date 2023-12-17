package com.hichamea.todolist.service;

import com.hichamea.todolist.dto.UserDto;

import java.util.List;

/**
 * Service interface for managing user-related operations.
 * This interface defines methods for managing and interacting with user entities and their operations.
 */
public interface UserService {

    /**
     * Retrieves all users.
     *
     * @return A list of UserDto representing all users.
     */
    List<UserDto> findAllUsers();

    /**
     * Retrieves user information by ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return A UserDto representing the retrieved user.
     * @throws EntityNotFoundException If the user with the specified ID is not found.
     */
    UserDto findUserById(Long userId);

    /**
     * Saves a user using the provided UserDto.
     *
     * @param user The UserDto to be saved.
     * @return The saved UserDto.
     * @throws InvalidEntityException If the provided UserDto contains invalid data.
     */
    UserDto saveUser(UserDto user);

    /**
     * Updates an existing user identified by its ID.
     *
     * @param userId The ID of the user to update.
     * @param user   The UserDto containing updated data.
     * @return A UserDto representing the updated user.
     * @throws EntityNotFoundException If the user with the specified ID is not found.
     * @throws InvalidEntityException  If the provided UserDto contains invalid data.
     */
    UserDto updateUser(Long userId, UserDto user);

    /**
     * Deletes a user by ID.
     *
     * @param userId The ID of the user to delete.
     * @throws EntityNotFoundException If the user with the specified ID is not found.
     */
    void deleteUserById(Long userId);
}
