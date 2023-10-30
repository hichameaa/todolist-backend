package com.hichamea.todolist.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * A class representing an error response.
 * This class contains attributes for HTTP status, error message, and date.
 */
@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private String date;
}
