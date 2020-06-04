package com.rentacar.advertisementservice.repository;

import com.rentacar.advertisementservice.domain.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {
}
