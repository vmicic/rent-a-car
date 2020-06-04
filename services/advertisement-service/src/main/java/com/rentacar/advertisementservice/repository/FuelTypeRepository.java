package com.rentacar.advertisementservice.repository;

import com.rentacar.advertisementservice.domain.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
}
