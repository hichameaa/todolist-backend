package com.hichamea.todolist.dto;

import com.hichamea.todolist.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

/**
 * A Data Transfer Object (DTO) representing a to-do item.
 * This class contains attributes for to-do item information and conversion methods to and from the corresponding entity.
 */
@Data
@Builder
public class TodoDto {

    private Long id;

    private String title;

    private String description;

    private ZonedDateTime startDate;

    private boolean done;

    private boolean favorite;

    private CategoryDto category;

    /**
     * Converts a TodoDto object to a Todo entity.
     *
     * @param todoDto The TodoDto object to convert.
     * @return The corresponding Todo entity.
     */
    public static Todo toEntity(TodoDto todoDto) {
        final Todo todo = new Todo();
        todo.setId(todoDto.getId());
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setDone(todoDto.isDone());
        todo.setFavorite(todoDto.isFavorite());
        todo.setStartDate(ZonedDateTime.now());
        todo.setCategory(CategoryDto.toEntity(todoDto.getCategory()));

        return todo;
    }

    /**
     * Converts a Todo entity to a TodoDto object.
     *
     * @param todo The Todo entity to convert.
     * @return The corresponding TodoDto object.
     */
    public static TodoDto fromEntity(Todo todo) {
        return TodoDto.builder()
                      .id(todo.getId())
                      .title(todo.getTitle())
                      .description(todo.getDescription())
                      .startDate(todo.getStartDate())
                      .done(todo.isDone())
                      .favorite(todo.isFavorite())
                      .build();
    }
}
