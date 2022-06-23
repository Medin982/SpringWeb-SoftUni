package com.example.coffeshop.config;

import com.example.coffeshop.models.entities.Category;
import com.example.coffeshop.models.enums.CategoryName;
import com.example.coffeshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class CategorySeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.categoryRepository.count() <= 0) {
            this.categoryRepository.
                    saveAll(Arrays.stream(CategoryName.values()).
                            map(Category::new).
                            collect(Collectors.toList()));
        }
    }
}
