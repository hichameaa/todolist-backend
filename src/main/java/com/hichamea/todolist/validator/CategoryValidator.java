package com.hichamea.todolist.validator;

import com.hichamea.todolist.dto.CategoryDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Validation class for CategoryDto objects.
 */
public class CategoryValidator {

    /**
     * Validates the required fields of the CategoryDto object.
     *
     * @param category The CategoryDto object to validate.
     * @return A list of error messages, or an empty list if validation succeeds.
     */
    public static List<String> validateCategoryFields(CategoryDto category) {
        List<String> errors = new ArrayList<>();

        // Check if the CategoryDto object is null
        if (category == null) {
            errors.add("Category object is null");
            return errors;
        }

        // Validate the required fields of the category
        errors.addAll(Stream.of(
                                    validateRequiredField("Name", category.getName()),
                                    validateRequiredField("Description", category.getDescription())
                            )
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
