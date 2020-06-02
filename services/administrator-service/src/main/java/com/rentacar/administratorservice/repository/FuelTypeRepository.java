package com.rentacar.administratorservice.repository;

import com.rentacar.administratorservice.domain.CarClass;
import com.rentacar.administratorservice.domain.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
    FuelType findByNameIgnoreCase(String name);
}
