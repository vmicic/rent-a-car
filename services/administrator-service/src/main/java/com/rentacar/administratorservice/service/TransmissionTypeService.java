package com.rentacar.administratorservice.service;

import com.rentacar.administratorservice.domain.CarClass;
import com.rentacar.administratorservice.domain.TransmissionType;

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
