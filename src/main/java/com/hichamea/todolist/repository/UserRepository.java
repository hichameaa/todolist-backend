package com.hichamea.todolist.repository;

import com.hichamea.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
