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

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

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
