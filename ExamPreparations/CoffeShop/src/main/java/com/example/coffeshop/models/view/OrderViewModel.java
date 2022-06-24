package com.example.coffeshop.models.view;

import com.example.coffeshop.models.entities.Category;
import com.example.coffeshop.models.enums.CategoryName;

import java.math.BigDecimal;

public class OrderViewModel {

    private long id;

    private String name;

    private BigDecimal price;

    private int neededTime;

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNeededTime() {
        return neededTime;
    }

    public void setNeededTime(int neededTime) {
        this.neededTime = neededTime;
    }
}
