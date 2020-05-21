package com.rentacar.orderservice.domain;

import javax.persistence.Entity;

@Entity
public class CarBrand extends BaseEntity {

    private String name;

    public CarBrand() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
