package com.rentacar.administratorservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CarBrand extends BaseEntity {

    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "carBrand", cascade = CascadeType.ALL)
    private List<CarModel> carModels = new ArrayList<>();

    public CarBrand() {
    }

    public List<CarModel> getCarModels() {
        return carModels;
    }

    public void setCarModels(List<CarModel> carModels) {
        this.carModels = carModels;
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
