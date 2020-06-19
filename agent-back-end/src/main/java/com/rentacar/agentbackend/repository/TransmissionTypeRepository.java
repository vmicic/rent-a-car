package com.rentacar.agentbackend.repository;

import com.rentacar.agentbackend.domain.TransmissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransmissionTypeRepository extends JpaRepository<TransmissionType, Long> {
	
    TransmissionType findByNameIgnoreCase(String name);
    
}
