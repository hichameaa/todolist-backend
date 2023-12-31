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
    List<Todo> findTodoByCategoryId(Long categoryId);

}
