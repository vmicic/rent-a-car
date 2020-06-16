package com.rentacar.agentbackend.service.admin;

import com.rentacar.agentbackend.domain.FuelType;
import com.rentacar.agentbackend.domain.TransmissionType;

import java.util.List;

public interface FuelTypeService {

    FuelType create(FuelType fuelType);

    List<FuelType> findAll();

    FuelType findById(Long id);

    FuelType findByName(String name);

    FuelType update(Long id, FuelType fuelType);

    void delete(Long id);

    boolean exists(Long id);
}
