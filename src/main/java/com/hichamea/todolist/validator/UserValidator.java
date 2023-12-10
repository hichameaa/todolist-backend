package com.hichamea.todolist.validator;

import com.hichamea.todolist.dto.UserDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Validation class for UserDto objects.
 */
public class UserValidator {

    /**
     * Validates the required fields of the UserDto object.
     *
     * @param user The UserDto object to validate.
     * @return A list of error messages, or an empty list if validation succeeds.
     */
    public static List<String> validateUserFields(UserDto user) {
        List<String> errors = new ArrayList<>();

        // Check if the UserDto object is null
        if (user == null) {
            errors.add("User object is null");
            return errors;
        }

        // Validate the required fields of the user
        errors.addAll(Stream.of(
                                    validateRequiredField("First name", user.getFirstName()),
                                    validateRequiredField("Last name", user.getLastName()),
                                    validateRequiredField("User name", user.getUserName()),
                                    validateRequiredField("User Email", user.getEmail()),
                                    validateRequiredField("User Password", user.getPassword())
                            )
                            .filter(Objects::nonNull)
                            .toList());

        return errors;
    }

    /**
     * Validates the required fields of the UserDto object for authentication.
     *
     * @param user The UserDto object to validate.
     * @return A list of error messages, or an empty list if validation succeeds.
     */
    public static List<String> validateUserAuthenticationFields(UserDto user) {
        List<String> errors = new ArrayList<>();

        // Check if the UserDto object is null
        if (user == null) {
            errors.add("User object is null");
            return errors;
        }

        // Validate the required fields of the user
        errors.addAll(Stream.of(
                                    validateRequiredField("User Email", user.getEmail()),
                                    validateRequiredField("User Password", user.getPassword())
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
