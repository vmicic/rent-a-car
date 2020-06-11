package com.rentacar.userservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CarModel extends  BaseEntity{

    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "car_brand_id", referencedColumnName = "id")
    private CarBrand carBrand;

    public CarModel() {
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
