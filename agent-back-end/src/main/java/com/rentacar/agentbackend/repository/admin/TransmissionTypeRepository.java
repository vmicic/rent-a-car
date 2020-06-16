package com.rentacar.agentbackend.repository.admin;

import com.rentacar.agentbackend.domain.CarClass;
import com.rentacar.agentbackend.domain.TransmissionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionTypeRepository extends JpaRepository<TransmissionType, Long> {
    TransmissionType findByNameIgnoreCase(String name);
}
