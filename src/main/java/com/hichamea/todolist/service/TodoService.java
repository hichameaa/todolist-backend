package com.hichamea.todolist.service;

import com.hichamea.todolist.dto.TodoDto;

import java.util.List;

/**
 * Service interface for managing to-do items.
 * This interface defines methods for managing and interacting with to-do item entities.
 */
public interface TodoService {

    /**
     * Retrieves all to-do items for a specific category.
     *
     * @param categoryId The ID of the category for which to retrieve to-do items.
     * @return A list of TodoDto representing the retrieved to-do items.
     */
    List<TodoDto> findAllTodosByCategory(Long categoryId);
}
