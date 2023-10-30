package com.hichamea.todolist.exception.handler;

import com.hichamea.todolist.exception.EntityNotFoundException;
import com.hichamea.todolist.exception.InvalidEntityException;
import com.hichamea.todolist.exception.handler.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Global exception handling for REST endpoints.
 * This class provides centralized error handling for exceptions that may occur in REST endpoints.
 */
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle exceptions of type EntityNotFoundException and return an error response.
     *
     * @param exception The EntityNotFoundException that occurred.
     * @return ResponseEntity with an error response and HTTP status.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(EntityNotFoundException exception) {

        logger.error(exception);

        var error = ErrorResponse.builder()
                                 .status(HttpStatus.NOT_FOUND)
                                 .message(exception.getMessage())
                                 .date(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                                 .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle exceptions of type InvalidEntityException and return an error response.
     *
     * @param exception The InvalidEntityException that occurred.
     * @return ResponseEntity with an error response and HTTP status.
     */
    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorResponse> handleException(InvalidEntityException exception) {

        logger.error(exception);

        var error = ErrorResponse.builder()
                                 .status(HttpStatus.BAD_REQUEST)
                                 .message(exception.getMessage())
                                 .date(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                                 .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
