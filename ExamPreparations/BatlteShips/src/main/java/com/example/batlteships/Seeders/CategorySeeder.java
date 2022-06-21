package com.example.batlteships.Seeders;

import com.example.batlteships.Models.Entities.Category;
import com.example.batlteships.Models.Enums.CategoryEnum;
import com.example.batlteships.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
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
        if (this.categoryRepository.count() == 0) {
            List<Category> categories = Arrays.stream(CategoryEnum.values())
                    .map(Category::new).toList();
            this.categoryRepository.saveAll(categories);
        }
    }
}
