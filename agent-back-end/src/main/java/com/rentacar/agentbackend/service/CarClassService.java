package com.rentacar.agentbackend.service;

import com.rentacar.agentbackend.domain.CarBrand;
import com.rentacar.agentbackend.domain.CarClass;

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
