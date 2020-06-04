package com.rentacar.advertisementservice.service;

import com.rentacar.advertisementservice.domain.FuelType;

public interface FuelTypeService {
    FuelType create(FuelType fuelType);

    boolean exists(Long id);

    FuelType findById(Long id);
}
