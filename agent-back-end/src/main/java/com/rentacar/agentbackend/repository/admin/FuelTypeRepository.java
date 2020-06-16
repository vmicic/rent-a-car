package com.rentacar.agentbackend.repository.admin;

import com.rentacar.agentbackend.domain.CarClass;
import com.rentacar.agentbackend.domain.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
    FuelType findByNameIgnoreCase(String name);
}
