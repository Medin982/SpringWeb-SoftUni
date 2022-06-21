package com.example.mobilelele.Models.DTO;

import com.example.mobilelele.Models.Entity.Enums.EngineEnum;
import com.example.mobilelele.Models.Entity.Enums.TransmissionEnum;

public class OfferDTO {

    private String brandName;

    private String modelName;

    private int mileage;

    private double price;

    private EngineEnum engineType;

    private TransmissionEnum transmissionType;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public EngineEnum getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineEnum engineType) {
        this.engineType = engineType;
    }

    public TransmissionEnum getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionEnum transmissionType) {
        this.transmissionType = transmissionType;
    }
}
