package com.hichamea.todolist.service.impl;

import com.hichamea.todolist.dto.CategoryDto;
import com.hichamea.todolist.dto.TodoDto;
import com.hichamea.todolist.exception.EntityNotFoundException;
import com.hichamea.todolist.exception.InvalidEntityException;
import com.hichamea.todolist.model.Category;
import com.hichamea.todolist.model.Todo;
import com.hichamea.todolist.repository.TodoRepository;
import com.hichamea.todolist.service.TodoService;
import com.hichamea.todolist.validator.TodoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Service implementation for managing categories.
 * This class provides the implementation for managing todo-related operations.
 */
@Service
@Slf4j
public class TodoServiceImpl implements TodoService {

    private static final String TODO_NOT_FOUND_ERROR = "Todos not found with ID: %d";

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<TodoDto> findAllTodosByCategory(Long todoId) {
        log.info("Fetching all todos for todo with ID: {}", todoId);
        List<TodoDto> todos = todoRepository.findTodoByCategoryId(todoId).stream()
                                            .map(TodoDto::fromEntity)
                                            .toList();
        log.info("Found {} todos for todo with ID: {}", todos.size(), todoId);
        return todos;
    }

    @Override
    public List<TodoDto> findAllTodos() {
        log.info("Fetching all todos from the database.");
        List<TodoDto> todos = todoRepository.findAll().stream()
                                            .map(TodoDto::fromEntity)
                                            .toList();
        log.info("Found {} todos.", todos.size());
        return todos;
    }

    @Override
    public TodoDto findTodoById(Long todoId) {
        log.info("Fetching todo with ID: {}", todoId);
        Todo todoFound = todoRepository.findById(todoId)
                                       .orElseThrow(() -> new EntityNotFoundException(TODO_NOT_FOUND_ERROR + todoId));
        log.info("Todo found with ID: {}", todoId);
        return TodoDto.fromEntity(todoFound);
    }

    @Override
    public TodoDto saveTodo(TodoDto todo) {
        List<String> validationErrors = TodoValidator.validateTodoFields(todo);

        if (!validationErrors.isEmpty()) {
            log.error("Todo validation failed for {} with errors: {}", todo, validationErrors);
            throw new InvalidEntityException("Todo validation failed");
        }

        TodoDto savedTodo = TodoDto.fromEntity(todoRepository.save(TodoDto.toEntity(todo)));
        log.info("Todo successfully created for todo ID {}", savedTodo.getId());
        return savedTodo;
    }

    @Override
    public TodoDto updateTodo(Long todoId, TodoDto todo) {
        List<String> validationMessages = TodoValidator.validateTodoFields(todo);

        if (!validationMessages.isEmpty()) {
            log.error("Failed to update todo with ID {} due to validation errors: {}", todoId, validationMessages);
            throw new InvalidEntityException("Todo validation failed");
        }

        Todo todoToUpdate = todoRepository.findById(todoId)
                                          .orElseThrow(() -> new EntityNotFoundException(TODO_NOT_FOUND_ERROR + todoId));

        updateTodoData(todoToUpdate, todo);

        TodoDto updatedTodo = TodoDto.fromEntity(todoRepository.save(todoToUpdate));
        log.info("Todo with ID {} successfully updated.", todo.getId());
        return updatedTodo;
    }

    @Override
    public void deleteTodoById(Long todoId) {
        Objects.requireNonNull(todoId, "Todo ID cannot be null");
        log.warn("Attempting to delete todo with ID: {}", todoId);

        Todo todoToDelete = todoRepository.findById(todoId)
                                          .orElseThrow(() -> new EntityNotFoundException(TODO_NOT_FOUND_ERROR + todoId));

        todoRepository.deleteById(todoToDelete.getId());
        log.info("Todo with ID {} successfully deleted.", todoId);
    }

    private void updateTodoData(Todo todoToUpdate, TodoDto todo) {
        todoToUpdate.setTitle(todo.getTitle());
        todoToUpdate.setDescription(todo.getDescription());
        todoToUpdate.setStartDate(todo.getStartDate());
        todoToUpdate.setDone(todo.isDone());
        todoToUpdate.setFavorite(todo.isFavorite());
        todoToUpdate.setCategory(CategoryDto.toEntity(todo.getCategory()));
    }
}
