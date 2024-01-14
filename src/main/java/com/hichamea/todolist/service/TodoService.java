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

    /**
     * Retrieves all to-do items.
     *
     * @return A list of TodoDto representing all the retrieved to-do items.
     */
    List<TodoDto> findAllTodos();

    /**
     * Retrieves to-do information by ID.
     *
     * @param todoId The ID of the to-do to retrieve.
     * @return TodoDto representing the retrieved to-do item.
     * @throws EntityNotFoundException If the to-do with the specified ID is not found.
     */
    TodoDto findTodoById(Long todoId);

    /**
     * Saves a new to-do item.
     *
     * @param to-do The TodoDto containing data for creating a new to-do.
     * @return TodoDto representing the saved to-do item.
     * @throws InvalidEntityException If the provided TodoDto contains invalid data.
     */
    TodoDto saveTodo(TodoDto todo);

    /**
     * Updates an existing to-do item.
     *
     * @param todoId The ID of the to-do to update.
     * @param to-do  The TodoDto containing updated data.
     * @return TodoDto representing the updated to-do item.
     * @throws EntityNotFoundException If the to-do with the specified ID is not found.
     * @throws InvalidEntityException  If the provided TodoDto contains invalid data.
     */
    TodoDto updateTodo(Long todoId, TodoDto todo);

    /**
     * Deletes a to-do item by ID.
     *
     * @param todoId The ID of the to-do to delete.
     * @throws EntityNotFoundException If the to-do with the specified ID is not found.
     */
    void deleteTodoById(Long todoId);
}
