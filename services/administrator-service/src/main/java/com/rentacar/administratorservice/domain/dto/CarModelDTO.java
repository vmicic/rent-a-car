package com.rentacar.administratorservice.domain.dto;

public class CarModelDTO {

    private String name;

    private Long carBrandId;

    public CarModelDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(Long carBrandId) {
        this.carBrandId = carBrandId;
    }
}
