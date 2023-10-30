package com.hichamea.todolist.exception;

/**
 * Custom exception class for invalid entity errors.
 * This exception is thrown when an entity or data is considered invalid in the system.
 */
public class InvalidEntityException extends RuntimeException {

    /**
     * Constructs a new InvalidEntityException with no specified detail message.
     */
    public InvalidEntityException() {
        super();
    }

    /**
     * Constructs a new InvalidEntityException with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public InvalidEntityException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidEntityException with the specified detail message and cause.
     *
     * @param message The detail message explaining the exception.
     * @param cause   The cause of the exception.
     */
    public InvalidEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
