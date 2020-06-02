package com.rentacar.administratorservice.domain;

import javax.persistence.Entity;

@Entity
public class CarClass extends BaseEntity {

    private String name;

    public CarClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
