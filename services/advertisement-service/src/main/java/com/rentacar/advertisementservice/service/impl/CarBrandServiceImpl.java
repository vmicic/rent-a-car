package com.rentacar.advertisementservice.service.impl;

import com.rentacar.advertisementservice.domain.CarBrand;
import com.rentacar.advertisementservice.repository.CarBrandRepository;
import com.rentacar.advertisementservice.service.CarBrandService;
import org.springframework.stereotype.Service;

@Service
public class CarBrandServiceImpl implements CarBrandService {

    private final CarBrandRepository carBrandRepository;


    public CarBrandServiceImpl(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }

    @Override
    public CarBrand create(CarBrand carBrand) {
        return this.carBrandRepository.save(carBrand);
    }

    @Override
    public boolean exists(Long id) {
        return this.carBrandRepository.findById(id).isPresent();
    }

    @Override
    public CarBrand findById(Long id) {
        return this.carBrandRepository.findById(id).orElse(null);
    }
}
