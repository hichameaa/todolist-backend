package com.hichamea.todolist.controller;

import com.hichamea.todolist.dto.CategoryDto;
import com.hichamea.todolist.dto.TodoDto;
import com.hichamea.todolist.service.CategoryService;
import com.hichamea.todolist.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing categories.
 * This class handles operations related to category management.
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private final TodoService todoService;

    /**
     * Constructor for CategoryController.
     *
     * @param categoryService The service responsible for category-related operations.
     * @param todoService     The service responsible for to-do-related operations.
     */
    public CategoryController(CategoryService categoryService, TodoService todoService) {
        this.categoryService = categoryService;
        this.todoService = todoService;
    }

    /**
     * Retrieves all categories.
     *
     * @return ResponseEntity with a list of CategoryDto and HTTP status.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<>(categoryService.findAllCategories(), HttpStatus.OK);
    }

    /**
     * Retrieves category information by ID.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return ResponseEntity with CategoryDto and HTTP status.
     */
    @GetMapping(value = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long categoryId) {
        return new ResponseEntity<>(categoryService.findCategoryById(categoryId), HttpStatus.OK);
    }

    /**
     * Endpoint for creating a new category.
     *
     * @param category The CategoryDto containing data for creating a new category.
     * @return ResponseEntity with CategoryDto and HTTP status.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto category) {
        return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.CREATED);
    }

    /**
     * Endpoint for updating an existing category.
     *
     * @param categoryId The ID of the category to update.
     * @param category   The CategoryDto containing updated data.
     * @return ResponseEntity with CategoryDto and HTTP status.
     */
    @PutMapping(value = "/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDto category) {
        return new ResponseEntity<>(categoryService.updateCategory(categoryId, category), HttpStatus.OK);
    }

    /**
     * Endpoint for deleting a category by ID.
     *
     * @param categoryId The ID of the category to delete.
     * @return ResponseEntity with HTTP status.
     */
    @DeleteMapping(value = "/{categoryId}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all Todos for a specific category.
     *
     * @param categoryId The ID of the category for which to retrieve Todos.
     * @return ResponseEntity with a list of TodoDto and HTTP status.
     */
    @GetMapping(value = "/{categoryId}/todos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TodoDto>> getAllTodosByCategory(Long categoryId) {
        return new ResponseEntity<>(todoService.findAllTodosByCategory(categoryId), HttpStatus.OK);
    }

    /**
     * Retrieves all Categories for a specific user.
     *
     * @param userId The ID of the user for which to retrieve Categories.
     * @return ResponseEntity with a list of CategoryDto and HTTP status.
     */
    @GetMapping(value = "users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryDto>> getAllCategoriesByUserId(Long userId) {
        return new ResponseEntity<>(categoryService.findAllCategoriesByUserId(userId), HttpStatus.OK);
    }

    /**
     * Retrieves all Todos for today for a specific user.
     *
     * @param userId The ID of the user for which to retrieve Todos.
     * @return ResponseEntity with a list of TodoDto and HTTP status.
     */
    @GetMapping(value = "todos/today/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryDto>> getAllTodosByCategoriesForToday(@PathVariable Long userId) {
        return new ResponseEntity<>(categoryService.findAllTodosByCategoriesForToday(userId), HttpStatus.OK);
    }
}
