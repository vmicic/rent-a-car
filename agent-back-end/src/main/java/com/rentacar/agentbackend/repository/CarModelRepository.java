package com.rentacar.agentbackend.repository;

import com.rentacar.agentbackend.domain.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    CarModel findByNameIgnoreCase(String name);
}
