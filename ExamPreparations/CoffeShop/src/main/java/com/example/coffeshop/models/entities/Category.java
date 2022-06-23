package com.example.coffeshop.models.entities;

import com.example.coffeshop.models.enums.CategoryName;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryName name;

    @Column(nullable = false, name = "needed_time")
    private int neededTime;

    public Category() {
    }

    public Category(CategoryName name) {
        this.name = name;
    }

    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    public int getNeededTime() {
        return neededTime;
    }

    public void setNeededTime(int neededTime) {
        this.neededTime = neededTime;
    }
}
