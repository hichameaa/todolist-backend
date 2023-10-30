package com.hichamea.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Todo List application.
 * This class serves as the entry point for the Spring Boot application.
 */
@SpringBootApplication
public class TodoListApplication {

    /**
     * Main method to start the Spring Boot application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(TodoListApplication.class, args);
    }

}
