package com.rentacar.agentbackend.service.impl;

import com.rentacar.agentbackend.domain.CarModel;
import com.rentacar.agentbackend.domain.dto.CarModelDTO;
import com.rentacar.agentbackend.repository.CarModelRepository;
import com.rentacar.agentbackend.service.CarBrandService;
import com.rentacar.agentbackend.service.CarModelService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarModelServiceImpl implements CarModelService {

    private final CarModelRepository carModelRepository;
    private final CarBrandService carBrandService;

    public CarModelServiceImpl(CarModelRepository carModelRepository, CarBrandService carBrandService) {
        this.carModelRepository = carModelRepository;
        this.carBrandService = carBrandService;
    }

    @Override
    public CarModel create(CarModelDTO carModelDTO) {
        CarModel carModel = new CarModel();

        carModel.setName(carModelDTO.getName());
        carModel.setCarBrand(carBrandService.findById(carModelDTO.getCarBrandId()));

        if(this.findByName(carModel.getName()) != null) {
            return this.findByName(carModel.getName());
        }

        return this.carModelRepository.save(carModel);
    }

    @Override
    public CarModel findById(Long id) {
        return this.carModelRepository.findById(id).orElse(null);
    }

    @Override
    public CarModel update(Long id, CarModel carModel) {
        Optional<CarModel> carModelOptional = this.carModelRepository.findById(id);

        if(!carModelOptional.isPresent()) {
            return null;
        }

        CarModel carModelToUpdate = carModelOptional.get();
        carModelToUpdate.setName(carModel.getName());

        return this.carModelRepository.save(carModelToUpdate);
    }

    @Override
    public void delete(Long id) {
        this.carModelRepository.deleteById(id);
    }

    @Override
    public CarModel findByName(String name) {
        return this.carModelRepository.findByNameIgnoreCase(name);
    }

    @Override
    public List<CarModel> findAll() {
        return this.carModelRepository.findAll();
    }

    @Override
    public boolean exists(Long id) {
        return this.carModelRepository.findById(id).isPresent();
    }
}
