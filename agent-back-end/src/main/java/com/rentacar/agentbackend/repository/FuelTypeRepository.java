package com.rentacar.agentbackend.repository;

import com.rentacar.agentbackend.domain.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
    FuelType findByNameIgnoreCase(String name);
}
