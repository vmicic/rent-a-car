package com.rentacar.advertisementservice.service;

import com.rentacar.advertisementservice.domain.CarBrand;

public interface CarBrandService {

    CarBrand create(CarBrand carBrand);

    boolean exists(Long id);

    CarBrand findById(Long id);
}
