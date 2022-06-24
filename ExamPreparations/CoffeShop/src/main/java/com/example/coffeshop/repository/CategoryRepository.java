package com.example.coffeshop.repository;

import com.example.coffeshop.models.entities.Category;
import com.example.coffeshop.models.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(CategoryName category);
}
