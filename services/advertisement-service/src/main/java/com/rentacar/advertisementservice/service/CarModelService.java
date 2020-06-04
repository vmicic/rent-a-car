package com.rentacar.advertisementservice.service;

import com.rentacar.advertisementservice.domain.CarModel;

public interface CarModelService {

    CarModel create(CarModel carModel);

    boolean exists(Long id);

    CarModel findById(Long id);
}
