package com.rentacar.administratorservice.service.impl;

import com.rentacar.administratorservice.domain.CarBrand;
import com.rentacar.administratorservice.domain.CarClass;
import com.rentacar.administratorservice.repository.CarClassRepository;
import com.rentacar.administratorservice.service.CarClassService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarClassServiceImpl implements CarClassService {

    private final CarClassRepository carClassRepository;

    public CarClassServiceImpl(CarClassRepository carClassRepository) {
        this.carClassRepository = carClassRepository;
    }


    @Override
    public CarClass create(CarClass carClass) {
        if(this.findByName(carClass.getName()) != null) {
            return this.findByName(carClass.getName());
        }

        return this.carClassRepository.save(carClass);
    }

    @Override
    public CarClass findById(Long id) {
        return this.carClassRepository.findById(id).orElse(null);
    }

    @Override
    public CarClass update(Long id, CarClass carClass) {
        Optional<CarClass> carClassOptional = this.carClassRepository.findById(id);

        if(!carClassOptional.isPresent()) {
            return null;
        }

        CarClass carClassToUpdate = carClassOptional.get();
        carClassToUpdate.setName(carClass.getName());

        return this.carClassRepository.save(carClassToUpdate);
    }

    @Override
    public void delete(Long id) {
        this.carClassRepository.deleteById(id);
    }

    @Override
    public CarClass findByName(String name) {
        return this.carClassRepository.findByNameIgnoreCase(name);
    }

    @Override
    public List<CarClass> findAll() {
        return this.carClassRepository.findAll();
    }

    @Override
    public boolean exists(Long id) {
        return this.carClassRepository.findById(id).isPresent();
    }
}
