package com.rentacar.advertisementservice.service;

import com.rentacar.advertisementservice.domain.CarModel;
import com.rentacar.advertisementservice.domain.TransmissionType;

public interface TransmissionTypeService {
    TransmissionType create(TransmissionType TransmissionType);

    boolean exists(Long id);

    TransmissionType findById(Long id);
}
