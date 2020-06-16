package com.rentacar.agentbackend.service.impl.admin;

import com.rentacar.agentbackend.domain.FuelType;
import com.rentacar.agentbackend.repository.admin.FuelTypeRepository;
import com.rentacar.agentbackend.service.admin.FuelTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    private final FuelTypeRepository fuelTypeRepository;


    public FuelTypeServiceImpl(FuelTypeRepository fuelTypeRepository) {
        this.fuelTypeRepository = fuelTypeRepository;
    }

    @Override
    public FuelType create(FuelType fuelType) {
        if(this.findByName(fuelType.getName()) != null) {
            return this.findByName(fuelType.getName());
        }

        return this.fuelTypeRepository.save(fuelType);
    }

    @Override
    public FuelType findById(Long id) {
        return this.fuelTypeRepository.findById(id).orElse(null);
    }

    @Override
    public FuelType update(Long id, FuelType fuelType) {
        Optional<FuelType> fuelTypeOptional = this.fuelTypeRepository.findById(id);

        if(!fuelTypeOptional.isPresent()) {
            return null;
        }

        FuelType fuelTypeToUpdate = fuelTypeOptional.get();
        fuelTypeToUpdate.setName(fuelType.getName());

        return this.fuelTypeRepository.save(fuelTypeToUpdate);
    }

    @Override
    public void delete(Long id) {
        this.fuelTypeRepository.deleteById(id);
    }

    @Override
    public FuelType findByName(String name) {
        return this.fuelTypeRepository.findByNameIgnoreCase(name);
    }

    @Override
    public List<FuelType> findAll() {
        return this.fuelTypeRepository.findAll();
    }

    @Override
    public boolean exists(Long id) {
        return this.fuelTypeRepository.findById(id).isPresent();
    }
}
