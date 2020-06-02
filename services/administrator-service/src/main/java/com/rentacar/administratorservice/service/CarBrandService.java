package com.rentacar.administratorservice.service;

import com.rentacar.administratorservice.domain.CarBrand;

import java.util.List;
import java.util.Set;

public interface CarBrandService {

    CarBrand create(CarBrand carBrand);

    List<CarBrand> findAll();

    CarBrand findById(Long id);

    CarBrand findByName(String name);

    CarBrand update(Long id, CarBrand carBrand);

    void delete(Long id);

    boolean exists(Long id);
}
