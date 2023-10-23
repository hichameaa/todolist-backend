package com.hichamea.todolist.service;

import com.hichamea.todolist.dto.UserDto;

public interface AuthenticationService {

    UserDto authenticate(UserDto user);
}
