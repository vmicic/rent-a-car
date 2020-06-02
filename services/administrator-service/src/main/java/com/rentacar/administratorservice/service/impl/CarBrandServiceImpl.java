package com.rentacar.administratorservice.service.impl;

import com.rentacar.administratorservice.domain.CarBrand;
import com.rentacar.administratorservice.repository.CarBrandRepository;
import com.rentacar.administratorservice.service.CarBrandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CarBrandServiceImpl implements CarBrandService {

    private final CarBrandRepository carBrandRepository;

    public CarBrandServiceImpl(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }

    @Override
    public CarBrand create(CarBrand carBrand) {
        if(this.findByName(carBrand.getName()) != null) {
            return this.findByName(carBrand.getName());
        }

        return this.carBrandRepository.save(carBrand);
    }

    @Override
    public CarBrand findById(Long id) {
        return null;
    }

    @Override
    public CarBrand update(CarBrand carBrand) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public CarBrand findByName(String name) {
        return this.carBrandRepository.findByNameIgnoreCase(name);
    }

    @Override
    public List<CarBrand> findAll() {
        return this.carBrandRepository.findAll();
    }
}
