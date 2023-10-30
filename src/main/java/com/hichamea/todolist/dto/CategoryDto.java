package com.hichamea.todolist.dto;

import com.hichamea.todolist.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A Data Transfer Object (DTO) representing a category.
 * This class contains attributes for category information and conversion methods to and from the corresponding entity.
 */
@Data
@Builder
public class CategoryDto {

    private Long id;

    private String name;

    private String description;

    private UserDto user;

    private List<TodoDto> todoList;

    /**
     * Converts a CategoryDto object to a Category entity.
     *
     * @param categoryDto The CategoryDto object to convert.
     * @return The corresponding Category entity.
     */
    public static Category toEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setUser(UserDto.toEntity(categoryDto.getUser()));
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return category;
    }

    /**
     * Converts a Category entity to a CategoryDto object.
     *
     * @param category The Category entity to convert.
     * @return The corresponding CategoryDto object.
     */
    public static CategoryDto fromEntity(Category category) {
        return CategoryDto.builder()
                          .id(category.getId())
                          .name(category.getName())
                          .description(category.getDescription())
                          .todoList(
                                  category.getTodoList() != null ? category.getTodoList()
                                                                           .stream()
                                                                           .map(TodoDto::fromEntity)
                                                                           .collect(Collectors.toList()) : null
                          )
                          .build();
    }
}
