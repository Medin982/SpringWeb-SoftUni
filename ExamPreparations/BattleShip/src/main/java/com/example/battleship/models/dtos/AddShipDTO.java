package com.example.battleship.models.dtos;

import com.example.battleship.models.enums.CategoryEnum;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AddShipDTO {

    @Size(min = 2, max = 10)
    @NotBlank
    private String name;

    @Positive
    private int power;

    @Positive
    private int health;

    @DateTimeFormat (pattern = "yyyy-MM-dd")
    @PastOrPresent
    private LocalDate created;

    private CategoryEnum category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}
