package com.rentacar.agentbackend.domain;

import javax.persistence.Entity;

@Entity
public class FuelType extends BaseEntity {

    private String name;

    public FuelType() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
