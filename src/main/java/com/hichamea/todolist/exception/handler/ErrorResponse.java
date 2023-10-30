package com.hichamea.todolist.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private String date;
}
