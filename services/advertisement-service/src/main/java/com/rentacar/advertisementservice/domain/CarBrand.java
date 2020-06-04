package com.rentacar.advertisementservice.domain;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CarBrand extends BaseEntity {

    @NotNull
    private String name;

    @OneToMany(mappedBy = "carBrand")
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
