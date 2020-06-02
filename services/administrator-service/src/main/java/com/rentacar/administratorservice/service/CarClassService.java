package com.rentacar.administratorservice.service;

import com.rentacar.administratorservice.domain.CarBrand;
import com.rentacar.administratorservice.domain.CarClass;

import java.util.List;

public interface CarClassService {
    CarClass create(CarClass carClass);

    List<CarClass> findAll();

    CarClass findById(Long id);

    CarClass findByName(String name);

    CarClass update(Long id, CarClass carBrand);

    void delete(Long id);

    boolean exists(Long id);
}
