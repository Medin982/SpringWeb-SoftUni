package com.example.shoppinglist.repository;

import com.example.shoppinglist.models.entities.Category;
import com.example.shoppinglist.models.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, String> {
    Optional<Category> findAllByName(CategoryName name);
}
