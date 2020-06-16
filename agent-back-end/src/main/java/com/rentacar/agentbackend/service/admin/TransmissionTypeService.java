package com.rentacar.agentbackend.service.admin;

import com.rentacar.agentbackend.domain.CarClass;
import com.rentacar.agentbackend.domain.TransmissionType;

import java.util.List;

public interface TransmissionTypeService {
    TransmissionType create(TransmissionType transmissionType);

    List<TransmissionType> findAll();

    TransmissionType findById(Long id);

    TransmissionType findByName(String name);

    TransmissionType update(Long id, TransmissionType transmissionType);

    void delete(Long id);

    boolean exists(Long id);
}
