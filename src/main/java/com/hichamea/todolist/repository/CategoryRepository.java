package com.hichamea.todolist.repository;

import com.hichamea.todolist.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Category entities.
 * This interface extends JpaRepository to provide CRUD (Create, Read, Update, Delete) operations
 * for Category entities with a primary key of type Long.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
