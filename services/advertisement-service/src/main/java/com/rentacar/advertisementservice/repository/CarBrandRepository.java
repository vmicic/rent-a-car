package com.rentacar.advertisementservice.repository;

import com.rentacar.advertisementservice.domain.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {
}
