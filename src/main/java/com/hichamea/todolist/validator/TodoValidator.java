package com.hichamea.todolist.validator;

import com.hichamea.todolist.dto.TodoDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Validation class for TodoDto objects.
 */
public class TodoValidator {

    /**
     * Validates the required fields of the TodoDto object.
     *
     * @param todo The TodoDto object to validate.
     * @return A list of error messages, or an empty list if validation succeeds.
     */
    public static List<String> validateTodoFields(TodoDto todo) {
        List<String> errors = new ArrayList<>();

        // Check if the TodoDto object is null
        if (todo == null) {
            errors.add("Todo object is null");
            return errors;
        }

        // Validate the required fields of the todo
        errors.addAll(Stream.of(
                                    validateRequiredField("Title", todo.getTitle()),
                                    validateRequiredField("Description", todo.getDescription()),
                                    validateRequiredField("Category ID", todo.getCategory()
                                                                             .getId()
                                                                             .toString()))
                            .filter(Objects::nonNull)
                            .toList());

        return errors;
    }

    /**
     * Validates that a required field is not empty.
     *
     * @param fieldName The name of the field to validate.
     * @param value     The value of the field.
     * @return An error message if the field is empty, otherwise null.
     */
    private static String validateRequiredField(String fieldName, String value) {
        if (StringUtils.isEmpty(value)) {
            return fieldName + " cannot be empty";
        }
        return null;
    }
}
