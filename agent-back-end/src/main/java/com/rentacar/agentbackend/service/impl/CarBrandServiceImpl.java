package com.rentacar.agentbackend.service.impl;

import com.rentacar.agentbackend.domain.CarBrand;
import com.rentacar.agentbackend.repository.CarBrandRepository;
import com.rentacar.agentbackend.service.CarBrandService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        return this.carBrandRepository.findById(id).orElse(null);
    }

    @Override
    public CarBrand update(Long id, CarBrand carBrand) {
        Optional<CarBrand> carBrandOptional = this.carBrandRepository.findById(id);

        if(!carBrandOptional.isPresent()) {
            return null;
        }

        CarBrand carBrandToUpdate = carBrandOptional.get();
        carBrandToUpdate.setName(carBrand.getName());

        return this.carBrandRepository.save(carBrandToUpdate);
    }

    @Override
    public void delete(Long id) {
        this.carBrandRepository.deleteById(id);
    }

    @Override
    public CarBrand findByName(String name) {
        return this.carBrandRepository.findByNameIgnoreCase(name);
    }

    @Override
    public List<CarBrand> findAll() {
        return this.carBrandRepository.findAll();
    }

    @Override
    public boolean exists(Long id) {
        return this.carBrandRepository.findById(id).isPresent();
    }
}
