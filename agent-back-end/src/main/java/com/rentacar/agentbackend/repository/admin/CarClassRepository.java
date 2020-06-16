package com.rentacar.agentbackend.repository.admin;

import com.rentacar.agentbackend.domain.CarBrand;
import com.rentacar.agentbackend.domain.CarClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarClassRepository extends JpaRepository<CarClass, Long> {
    CarClass findByNameIgnoreCase(String name);
}
