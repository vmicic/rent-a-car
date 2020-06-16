package com.rentacar.agentbackend.repository.admin;

import com.rentacar.agentbackend.domain.CarClass;
import com.rentacar.agentbackend.domain.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    CarModel findByNameIgnoreCase(String name);
}
