package com.rentacar.administratorservice.repository;

import com.rentacar.administratorservice.domain.CarClass;
import com.rentacar.administratorservice.domain.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    CarModel findByNameIgnoreCase(String name);
}
