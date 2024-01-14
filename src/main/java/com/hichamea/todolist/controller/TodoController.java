package com.hichamea.todolist.controller;

import com.hichamea.todolist.dto.TodoDto;
import com.hichamea.todolist.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing to-do items.
 * This class handles operations related to to-do item management.
 */
@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    /**
     * Constructor for TodoController.
     *
     * @param todoService The service responsible for to-do-related operations.
     */
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Retrieves all todos.
     *
     * @return ResponseEntity with a list of TodoDto and HTTP status.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        return new ResponseEntity<>(todoService.findAllTodos(), HttpStatus.OK);
    }

    /**
     * Retrieves to-do information by ID.
     *
     * @param todoId The ID of the to-do to retrieve.
     * @return ResponseEntity with TodoDto and HTTP status.
     */
    @GetMapping(value = "/{todoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long todoId) {
        return new ResponseEntity<>(todoService.findTodoById(todoId), HttpStatus.OK);
    }

    /**
     * Endpoint for creating a new to-do.
     *
     * @param to-do The TodoDto containing data for creating a new to-do.
     * @return ResponseEntity with TodoDto and HTTP status.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todo) {
        return new ResponseEntity<>(todoService.saveTodo(todo), HttpStatus.CREATED);
    }

    /**
     * Endpoint for updating an existing to-do.
     *
     * @param todoId The ID of the to-do to update.
     * @param to-do  The TodoDto containing updated data.
     * @return ResponseEntity with TodoDto and HTTP status.
     */
    @PutMapping(value = "/{todoId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long todoId, @RequestBody TodoDto todo) {
        return new ResponseEntity<>(todoService.updateTodo(todoId, todo), HttpStatus.OK);
    }

    /**
     * Endpoint for deleting a to-do by ID.
     *
     * @param todoId The ID of the to-do to delete.
     * @return ResponseEntity with HTTP status.
     */
    @DeleteMapping(value = "/{todoId}")
    public ResponseEntity<TodoDto> deleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodoById(todoId);
        return ResponseEntity.noContent().build();
    }
}
