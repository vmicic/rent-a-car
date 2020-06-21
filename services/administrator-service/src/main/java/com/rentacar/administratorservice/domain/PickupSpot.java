package com.rentacar.administratorservice.domain;

import javax.persistence.Entity;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PickupSpot that = (PickupSpot) o;
        return Objects.equals(street, that.street) &&
                Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city);
    }

    @Override
    public String toString() {
        return "PickupSpot{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
