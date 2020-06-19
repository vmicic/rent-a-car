package com.rentacar.agentbackend.repository;

import com.rentacar.agentbackend.domain.CarClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarClassRepository extends JpaRepository<CarClass, Long> {
    CarClass findByNameIgnoreCase(String name);
}
