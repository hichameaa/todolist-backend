package com.hichamea.todolist.controller;

import com.hichamea.todolist.dto.CategoryDto;
import com.hichamea.todolist.service.CategoryService;
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

    /**
     * Constructor for CategoryController.
     *
     * @param categoryService The service responsible for category-related operations.
     */
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
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
}
