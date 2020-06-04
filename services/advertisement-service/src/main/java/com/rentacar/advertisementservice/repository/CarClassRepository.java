package com.rentacar.advertisementservice.repository;

import com.rentacar.advertisementservice.domain.CarClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarClassRepository extends JpaRepository<CarClass, Long> {
}
