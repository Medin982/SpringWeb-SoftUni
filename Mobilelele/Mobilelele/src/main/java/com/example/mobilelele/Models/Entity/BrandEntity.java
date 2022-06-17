package com.example.mobilelele.Models.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand", targetEntity = ModelEntity.class, cascade = CascadeType.ALL)
    private List<ModelEntity> models;

    public BrandEntity() {
    }

    public List<ModelEntity> getModels() {
        return models;
    }

    public void setModels(List<ModelEntity> models) {
        this.models = models;
    }

    public BrandEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
