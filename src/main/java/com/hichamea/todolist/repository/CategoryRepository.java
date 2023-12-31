package com.hichamea.todolist.repository;

import com.hichamea.todolist.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Repository interface for managing Category entities.
 * This interface extends JpaRepository to provide CRUD (Create, Read, Update, Delete) operations
 * for Category entities with a primary key of type Long.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findCategoryByUserId(Long userId);

    @Query("select c from Category c inner join Todo t on t.category.id = c.id where t.startDate >= :startDate and t.startDate <= :endDate and c.user.id = :userId")
    List<Category> findAllTodosByCategoriesForToday(@Param("startDate") ZonedDateTime startDate, @Param("endDate") ZonedDateTime endDate, @Param("userId") Long userId);
}
