package com.hichamea.todolist.repository;

import com.hichamea.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing User entities.
 * This interface extends JpaRepository to provide CRUD (Create, Read, Update, Delete) operations
 * for User entities with a primary key of type Long.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by their email and password.
     *
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return An Optional containing the user if found, or an empty Optional if not found.
     */
    Optional<User> findUserByEmailAndPassword(String email, String password);
}
