package com.example.shoppinglist.models.dtos;

import com.example.shoppinglist.models.enums.CategoryName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDTO {
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Size(min = 5, message = "Description min length must be minimum 5 characters")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "Date cannot be in the past")
    private LocalDateTime neededBefore;

    @Positive(message = "Price must be positive number")
    @NotNull
    private BigDecimal price;

    @NotNull(message = "You must select the category")
    private CategoryName category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public void setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryName getCategory() {
        return category;
    }

    public void setCategory(CategoryName category) {
        this.category = category;
    }
}
