package com.hichamea.todolist.repository;

import com.hichamea.todolist.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
