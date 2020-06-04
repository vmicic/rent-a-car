package com.rentacar.advertisementservice.service;

import com.rentacar.advertisementservice.domain.CarClass;

public interface CarClassService {

    CarClass create(CarClass carClass);

    boolean exists(Long id);

    CarClass findById(Long id);
}
