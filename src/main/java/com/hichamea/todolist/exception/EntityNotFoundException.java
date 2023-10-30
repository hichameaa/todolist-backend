package com.hichamea.todolist.exception;

/**
 * Custom exception class for entity not found errors.
 * This exception is thrown when an entity or resource is not found in the system.
 */
public class EntityNotFoundException extends RuntimeException {

    /**
     * Constructs a new EntityNotFoundException with no specified detail message.
     */
    public EntityNotFoundException() {
        super();
    }

    /**
     * Constructs a new EntityNotFoundException with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new EntityNotFoundException with the specified detail message and cause.
     *
     * @param message The detail message explaining the exception.
     * @param cause   The cause of the exception.
     */
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
