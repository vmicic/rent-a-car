package com.rentacar.searchservice.domain;

import javax.persistence.Entity;

@Entity
public class FuelType extends BaseEntity {

    private String name;

    public FuelType() {
    }
}
