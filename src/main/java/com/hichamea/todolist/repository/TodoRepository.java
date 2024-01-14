package com.hichamea.todolist.repository;

import com.hichamea.todolist.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Category entities.
 * This interface extends JpaRepository to provide CRUD (Create, Read, Update, Delete) operations
 * for Category entities with a primary key of type Long.
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {

    /**
     * Finds todos by category ID.
     *
     * @param categoryId The ID of the category for which to retrieve todos.
     * @return A list of todos belonging to the specified category.
     */
    List<Todo> findTodoByCategoryId(Long categoryId);

}
