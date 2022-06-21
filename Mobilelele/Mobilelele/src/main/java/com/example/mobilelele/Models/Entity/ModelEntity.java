package com.example.mobilelele.Models.Entity;

import com.example.mobilelele.Models.Entity.Enums.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryEnum categoryEnum;

    @Column(length = 512)
    private String imageUrl;

    @Column(nullable = false)
    private int startYear;

    @Column(nullable = false)
    private int endYear;

    @ManyToOne
    private BrandEntity brand;

//    public ModelEntity() {
//    }
//
//    public ModelEntity(String name, CategoryEnum categoryEnum,
//                       String imageUrl, int startYear, int endYear, BrandEntity brand) {
//        this.name = name;
//        this.categoryEnum = categoryEnum;
//        this.imageUrl = imageUrl;
//        this.startYear = startYear;
//        this.endYear = endYear;
//        this.brand = brand;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }
}
