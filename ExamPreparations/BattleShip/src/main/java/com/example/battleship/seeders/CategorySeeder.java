package com.example.battleship.seeders;

import com.example.battleship.models.entities.Category;
import com.example.battleship.models.enums.CategoryEnums;
import com.example.battleship.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CategorySeeder implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.categoryRepository.count() == 0) {
            List<Category> categories = Arrays.stream(CategoryEnums.values())
                    .map(Category::new).toList();
            this.categoryRepository.saveAll(categories);
        }
    }
}
