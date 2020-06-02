package com.rentacar.administratorservice.repository;

import com.rentacar.administratorservice.domain.CarBrand;
import com.rentacar.administratorservice.domain.CarClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarClassRepository extends JpaRepository<CarClass, Long> {
    CarClass findByNameIgnoreCase(String name);
}
