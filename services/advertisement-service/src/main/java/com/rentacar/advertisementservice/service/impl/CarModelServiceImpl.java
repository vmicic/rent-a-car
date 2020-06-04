package com.rentacar.advertisementservice.service.impl;

import com.rentacar.advertisementservice.domain.CarModel;
import com.rentacar.advertisementservice.repository.CarModelRepository;
import com.rentacar.advertisementservice.service.CarModelService;
import org.springframework.stereotype.Service;

@Service
public class CarModelServiceImpl implements CarModelService {

    private final CarModelRepository carModelRepository;

    public CarModelServiceImpl(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    @Override
    public CarModel create(CarModel carModel) {
        return this.carModelRepository.save(carModel);
    }

    @Override
    public boolean exists(Long id) {
        return this.carModelRepository.findById(id).isPresent();
    }

    @Override
    public CarModel findById(Long id) {
        return this.carModelRepository.findById(id).orElse(null);
    }
}
