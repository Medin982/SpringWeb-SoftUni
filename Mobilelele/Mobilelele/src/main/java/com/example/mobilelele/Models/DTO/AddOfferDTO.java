package com.example.mobilelele.Models.DTO;

import com.example.mobilelele.Models.Entity.Enums.EngineEnum;
import com.example.mobilelele.Models.Entity.Enums.TransmissionEnum;

import javax.validation.constraints.NotNull;

public class AddOfferDTO {

    private Long modelId;

    private String imageURL;

    private double price;

    private int year;

    private int mileage;

    private String description;
    @NotNull
    private EngineEnum engine;
    @NotNull
    private TransmissionEnum transmission;

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public void setEngine(EngineEnum engineEnum) {
        this.engine = engineEnum;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
    }
}
