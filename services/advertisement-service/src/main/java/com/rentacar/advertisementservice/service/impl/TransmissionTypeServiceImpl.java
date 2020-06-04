package com.rentacar.advertisementservice.service.impl;

import com.rentacar.advertisementservice.domain.CarBrand;
import com.rentacar.advertisementservice.domain.TransmissionType;
import com.rentacar.advertisementservice.repository.TransmissionTypeRepository;
import com.rentacar.advertisementservice.service.TransmissionTypeService;
import org.springframework.stereotype.Service;

@Service
public class TransmissionTypeServiceImpl implements TransmissionTypeService {

    private final TransmissionTypeRepository transmissionTypeRepository;

    public TransmissionTypeServiceImpl(TransmissionTypeRepository transmissionTypeRepository) {
        this.transmissionTypeRepository = transmissionTypeRepository;
    }

    @Override
    public TransmissionType create(TransmissionType transmissionType) {
        return this.transmissionTypeRepository.save(transmissionType);
    }

    @Override
    public boolean exists(Long id) {
        return this.transmissionTypeRepository.findById(id).isPresent();
    }

    @Override
    public TransmissionType findById(Long id) {
        return this.transmissionTypeRepository.findById(id).orElse(null);
    }
}
