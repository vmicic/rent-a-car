package com.rentacar.agentbackend.repository;

import com.rentacar.agentbackend.domain.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {

    CarBrand findByNameIgnoreCase(String name);
}
