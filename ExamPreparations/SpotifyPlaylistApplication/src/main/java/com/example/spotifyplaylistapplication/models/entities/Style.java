package com.example.spotifyplaylistapplication.models.entities;

import com.example.spotifyplaylistapplication.models.enums.StyleType;

import javax.persistence.*;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity{

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private StyleType name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Style(StyleType name) {
        this.name = name;
    }

    public Style() {
    }

    public StyleType getName() {
        return name;
    }

    public void setName(StyleType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
