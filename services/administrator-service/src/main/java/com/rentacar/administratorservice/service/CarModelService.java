package com.rentacar.administratorservice.service;

import com.rentacar.administratorservice.domain.CarModel;
import com.rentacar.administratorservice.domain.dto.CarModelDTO;

import java.util.List;

public interface CarModelService {

    CarModel create(CarModelDTO carModelDTO);

    List<CarModel> findAll();

    CarModel findById(Long id);

    CarModel findByName(String name);

    CarModel update(Long id, CarModel carModel);

    void delete(Long id);

    boolean exists(Long id);
}
