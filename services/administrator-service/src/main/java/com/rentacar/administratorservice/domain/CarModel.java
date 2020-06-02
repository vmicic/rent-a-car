package com.rentacar.administratorservice.domain;

import javax.persistence.Entity;

@Entity
public class CarModel extends  BaseEntity{

    private String name;

    public CarModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
