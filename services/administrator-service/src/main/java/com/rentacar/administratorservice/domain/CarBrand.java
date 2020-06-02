package com.rentacar.administratorservice.domain;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.UniqueConstraint;

@Entity
public class CarBrand extends BaseEntity {

    @NotNull
    private String name;

    public CarBrand() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CarBrand{" +
                "name='" + name + '\'' +
                '}';
    }
}
