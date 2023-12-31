package com.hichamea.todolist.service.impl;

import com.hichamea.todolist.dto.CategoryDto;
import com.hichamea.todolist.dto.TodoDto;
import com.hichamea.todolist.exception.EntityNotFoundException;
import com.hichamea.todolist.model.Category;
import com.hichamea.todolist.model.Todo;
import com.hichamea.todolist.repository.CategoryRepository;
import com.hichamea.todolist.repository.TodoRepository;
import com.hichamea.todolist.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing categories.
 * This class provides the implementation for managing category-related operations.
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
    public List<TodoDto> findAllTodosByCategory(Long categoryId) {
        log.info("Fetching all todos for category with ID: {}", categoryId);
        List<TodoDto> todos = todoRepository.findTodoByCategoryId(categoryId).stream()
                                            .map(TodoDto::fromEntity)
                                            .toList();
        log.info("Found {} todos for category with ID: {}", todos.size(), categoryId);
        return todos;
    }
}
