package com.rentacar.administratorservice.repository;

import com.rentacar.administratorservice.domain.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {

    CarBrand findByNameIgnoreCase(String name);
}
