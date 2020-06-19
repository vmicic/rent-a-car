package com.rentacar.agentbackend.service.impl;

import com.rentacar.agentbackend.domain.TransmissionType;
import com.rentacar.agentbackend.repository.TransmissionTypeRepository;
import com.rentacar.agentbackend.service.TransmissionTypeService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransmissionTypeServiceImpl implements TransmissionTypeService {

    private final TransmissionTypeRepository transmissionTypeRepository;

    public TransmissionTypeServiceImpl(TransmissionTypeRepository transmissionTypeRepository) {
        this.transmissionTypeRepository = transmissionTypeRepository;
    }

    @Override
    public TransmissionType create(TransmissionType transmissionType) {
        if(this.findByName(transmissionType.getName()) != null) {
            return this.findByName(transmissionType.getName());
        }

        return this.transmissionTypeRepository.save(transmissionType);
    }

    @Override
    public TransmissionType findById(Long id) {
        return this.transmissionTypeRepository.findById(id).orElse(null);
    }

    @Override
    public TransmissionType update(Long id, TransmissionType transmissionType) {
        Optional<TransmissionType> transmissionTypeOptional = this.transmissionTypeRepository.findById(id);

        if(!transmissionTypeOptional.isPresent()) {
            return null;
        }

        TransmissionType transmissionTypeToUpdate = transmissionTypeOptional.get();
        transmissionTypeToUpdate.setName(transmissionType.getName());

        return this.transmissionTypeRepository.save(transmissionTypeToUpdate);
    }

    @Override
    public void delete(Long id) {
        this.transmissionTypeRepository.deleteById(id);
    }

    @Override
    public TransmissionType findByName(String name) {
        return this.transmissionTypeRepository.findByNameIgnoreCase(name);
    }

    @Override
    public List<TransmissionType> findAll() {
        return this.transmissionTypeRepository.findAll();
    }

    @Override
    public boolean exists(Long id) {
        return this.transmissionTypeRepository.findById(id).isPresent();
    }
}
