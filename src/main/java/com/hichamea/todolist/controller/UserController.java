package com.hichamea.todolist.controller;

import com.hichamea.todolist.dto.UserDto;
import com.hichamea.todolist.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing user-related operations.
 * This class handles operations related to user management.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * Constructor for the UserController.
     *
     * @param userService The UserService responsible for user-related operations.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return ResponseEntity with a list of UserDto and HTTP status.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    /**
     * Retrieves user information by ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return ResponseEntity with UserDto and HTTP status.
     */
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
    }

    /**
     * Endpoint for creating a new user.
     *
     * @param user The UserDto containing data for creating a new user.
     * @return ResponseEntity with UserDto and HTTP status.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    /**
     * Endpoint for updating an existing user.
     *
     * @param userId The ID of the user to update.
     * @param user   The UserDto containing updated data.
     * @return ResponseEntity with UserDto and HTTP status.
     */
    @PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserDto user) {
        return new ResponseEntity<>(userService.updateUser(userId, user), HttpStatus.OK);
    }

    /**
     * Endpoint for deleting a user by ID.
     *
     * @param userId The ID of the user to delete.
     * @return ResponseEntity with HTTP status.
     */
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
}
