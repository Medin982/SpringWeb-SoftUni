package com.example.battleship.models.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
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

    @PositiveOrZero
    private Integer category = -1;

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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
