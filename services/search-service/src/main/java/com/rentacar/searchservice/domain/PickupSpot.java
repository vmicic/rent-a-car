package com.rentacar.searchservice.domain;

import javax.persistence.Entity;

@Entity
public class PickupSpot extends BaseEntity {

    private String street;

    private String city;

    public PickupSpot() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
