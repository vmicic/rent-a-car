package com.rentacar.agentbackend.service;

import com.rentacar.agentbackend.domain.CarBrand;

import java.util.List;

public interface CarBrandService {

    CarBrand create(CarBrand carBrand);

    List<CarBrand> findAll();

    CarBrand findById(Long id);

    CarBrand findByName(String name);

    CarBrand update(Long id, CarBrand carBrand);

    void delete(Long id);

    boolean exists(Long id);
}
