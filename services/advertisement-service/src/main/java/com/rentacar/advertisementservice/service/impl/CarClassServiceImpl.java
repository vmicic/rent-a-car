package com.rentacar.advertisementservice.service.impl;

import com.rentacar.advertisementservice.domain.CarBrand;
import com.rentacar.advertisementservice.domain.CarClass;
import com.rentacar.advertisementservice.repository.CarClassRepository;
import com.rentacar.advertisementservice.service.CarClassService;
import org.springframework.stereotype.Service;

@Service
public class CarClassServiceImpl implements CarClassService {

    private final CarClassRepository carClassRepository;

    public CarClassServiceImpl(CarClassRepository carClassRepository) {
        this.carClassRepository = carClassRepository;
    }


    @Override
    public CarClass create(CarClass carClass) {
        return this.carClassRepository.save(carClass);
    }

    @Override
    public boolean exists(Long id) {
        return this.carClassRepository.findById(id).isPresent();
    }

    @Override
    public CarClass findById(Long id) {
        return this.carClassRepository.findById(id).orElse(null);
    }
}
