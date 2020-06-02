package com.rentacar.administratorservice.repository;

import com.rentacar.administratorservice.domain.CarClass;
import com.rentacar.administratorservice.domain.TransmissionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionTypeRepository extends JpaRepository<TransmissionType, Long> {
    TransmissionType findByNameIgnoreCase(String name);
}
