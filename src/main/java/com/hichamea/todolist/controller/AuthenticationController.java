package com.hichamea.todolist.controller;

import com.hichamea.todolist.dto.UserDto;
import com.hichamea.todolist.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for authentication-related operations.
 * This class handles authentication requests for user login.
 */
@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * Constructor for the AuthenticationController.
     *
     * @param authenticationService The AuthenticationService to handle authentication.
     */
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Endpoint for user authentication.
     *
     * @param user The UserDto containing user authentication data.
     * @return ResponseEntity with UserDto and HTTP status.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> authenticate(@RequestBody UserDto user) {
        return new ResponseEntity<>(authenticationService.authenticate(user), HttpStatus.OK);
    }
}
