package com.rentacar.advertisementservice.domain;

import javax.persistence.Entity;

@Entity
public class TransmissionType extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
