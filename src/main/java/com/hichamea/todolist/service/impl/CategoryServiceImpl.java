package com.hichamea.todolist.service.impl;

import com.hichamea.todolist.dto.CategoryDto;
import com.hichamea.todolist.exception.EntityNotFoundException;
import com.hichamea.todolist.exception.InvalidEntityException;
import com.hichamea.todolist.model.Category;
import com.hichamea.todolist.repository.CategoryRepository;
import com.hichamea.todolist.service.CategoryService;
import com.hichamea.todolist.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Service implementation for managing categories.
 * This class provides the implementation for managing category-related operations.
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORY_NOT_FOUND_ERROR = "Category not found with ID: %d";

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> findAllCategories() {
        log.info("Fetching all categories from the database.");
        List<CategoryDto> categories = categoryRepository.findAll().stream()
                                                         .map(CategoryDto::fromEntity)
                                                         .toList();
        log.info("Found {} categories.", categories.size());
        return categories;
    }

    @Override
    public CategoryDto findCategoryById(Long categoryId) {
        log.info("Fetching category with ID: {}", categoryId);
        Category categoryFound = categoryRepository.findById(categoryId)
                                                   .orElseThrow(() -> new EntityNotFoundException(CATEGORY_NOT_FOUND_ERROR + categoryId));
        log.info("Category found with ID: {}", categoryId);
        return CategoryDto.fromEntity(categoryFound);
    }

    @Override
    public CategoryDto saveCategory(CategoryDto category) {
        List<String> validationErrors = CategoryValidator.validateCategoryFields(category);

        if (!validationErrors.isEmpty()) {
            log.error("Category validation failed for {} with errors: {}", category, validationErrors);
            throw new InvalidEntityException("Category validation failed");
        }

        CategoryDto savedCategory = CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(category)));
        log.info("Category successfully created for category ID {}", savedCategory.getId());
        return savedCategory;
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto category) {

        List<String> validationMessages = CategoryValidator.validateCategoryFields(category);

        if (!validationMessages.isEmpty()) {
            log.error("Failed to update category with ID {} due to validation errors: {}", categoryId, validationMessages);
            throw new InvalidEntityException("Category validation failed");
        }

        Category categoryToUpdate = categoryRepository.findById(categoryId)
                                                      .orElseThrow(() -> new EntityNotFoundException(CATEGORY_NOT_FOUND_ERROR + categoryId));

        updateCategoryData(categoryToUpdate, category);

        CategoryDto updatedCategory = CategoryDto.fromEntity(categoryRepository.save(categoryToUpdate));
        log.info("Category with ID {} successfully updated.", category.getId());
        return updatedCategory;
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        Objects.requireNonNull(categoryId, "Category ID cannot be null");
        log.warn("Attempting to delete category with ID: {}", categoryId);

        Category categoryToDelete = categoryRepository.findById(categoryId)
                                                      .orElseThrow(() -> new EntityNotFoundException(CATEGORY_NOT_FOUND_ERROR + categoryId));

        categoryRepository.deleteById(categoryToDelete.getId());
        log.info("Category with ID {} successfully deleted.", categoryId);
    }

    private void updateCategoryData(Category categoryToUpdate, CategoryDto category) {
        categoryToUpdate.setName(category.getName());
        categoryToUpdate.setDescription(category.getDescription());
    }
}
