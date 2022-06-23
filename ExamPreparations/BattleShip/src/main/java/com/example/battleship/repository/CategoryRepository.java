package com.example.battleship.repository;

import com.example.battleship.models.entities.Category;
import com.example.battleship.models.enums.CategoryEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByName(String name);
    Category findByName(CategoryEnums name);
}
