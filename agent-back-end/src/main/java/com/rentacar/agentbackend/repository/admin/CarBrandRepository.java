package com.rentacar.agentbackend.repository.admin;

import com.rentacar.agentbackend.domain.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {

    CarBrand findByNameIgnoreCase(String name);
}
