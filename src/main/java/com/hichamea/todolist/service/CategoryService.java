package com.hichamea.todolist.service;

import com.hichamea.todolist.dto.CategoryDto;

import java.util.List;

/**
 * Service interface for managing categories.
 * This interface defines methods for managing and interacting with category entities.
 */
public interface CategoryService {

    /**
     * Retrieves all categories.
     *
     * @return A list of CategoryDto representing all categories.
     */
    List<CategoryDto> findAllCategories();

    /**
     * Retrieves category information by ID.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return A CategoryDto representing the retrieved category.
     * @throws EntityNotFoundException If the category with the specified ID is not found.
     */
    CategoryDto findCategoryById(Long categoryId);

    /**
     * Creates a new category.
     *
     * @param category The CategoryDto containing data for creating a new category.
     * @return A CategoryDto representing the created category.
     * @throws InvalidEntityException If the provided CategoryDto contains invalid data.
     */
    CategoryDto saveCategory(CategoryDto category);

    /**
     * Updates an existing category identified by its ID.
     *
     * @param categoryId The ID of the category to update.
     * @param category   The CategoryDto containing updated data.
     * @return A CategoryDto representing the updated category.
     * @throws EntityNotFoundException If the category with the specified ID is not found.
     * @throws InvalidEntityException  If the provided CategoryDto contains invalid data.
     */
    CategoryDto updateCategory(Long categoryId, CategoryDto category);

    /**
     * Deletes a category by ID.
     *
     * @param categoryId The ID of the category to delete.
     * @throws EntityNotFoundException If the category with the specified ID is not found.
     */
    void deleteCategoryById(Long categoryId);

    /**
     * Retrieves all categories associated with a user.
     *
     * @param userId The ID of the user.
     * @return A list of CategoryDto representing all categories associated with the user.
     */
    List<CategoryDto> findAllCategoriesByUserId(Long userId);

    /**
     * Retrieves all to-do items for today for a specific user across multiple categories.
     *
     * @param userId The ID of the user for which to retrieve to-do items.
     * @return A list of TodoDto representing the retrieved to-do items for today.
     */
    List<CategoryDto> findAllTodosByCategoriesForToday(Long userId);
}
