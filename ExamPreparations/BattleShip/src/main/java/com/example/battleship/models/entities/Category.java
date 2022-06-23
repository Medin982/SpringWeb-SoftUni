package com.example.battleship.models.entities;

import com.example.battleship.models.enums.CategoryEnums;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(
            name = "uuid-string",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false)
    private String id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.ORDINAL)
    private CategoryEnums name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Category(CategoryEnums name) {
        this.name = name;
    }

    public Category() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CategoryEnums getName() {
        return name;
    }

    public void setName(CategoryEnums name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
