package com.rentacar.advertisementservice.repository;

import com.rentacar.advertisementservice.domain.TransmissionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionTypeRepository extends JpaRepository<TransmissionType, Long> {
}
