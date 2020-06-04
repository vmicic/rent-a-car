package com.rentacar.advertisementservice.service.impl;

import com.rentacar.advertisementservice.domain.CarBrand;
import com.rentacar.advertisementservice.domain.FuelType;
import com.rentacar.advertisementservice.repository.FuelTypeRepository;
import com.rentacar.advertisementservice.service.FuelTypeService;
import org.springframework.stereotype.Service;

@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    private final FuelTypeRepository fuelTypeRepository;

    public FuelTypeServiceImpl(FuelTypeRepository fuelTypeRepository) {
        this.fuelTypeRepository = fuelTypeRepository;
    }


    @Override
    public FuelType create(FuelType fuelType) {
        return this.fuelTypeRepository.save(fuelType);
    }

    @Override
    public boolean exists(Long id) {
        return this.fuelTypeRepository.findById(id).isPresent();
    }

    @Override
    public FuelType findById(Long id) {
        return this.fuelTypeRepository.findById(id).orElse(null);
    }
}
