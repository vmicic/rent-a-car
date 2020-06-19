package com.rentacar.agentbackend.service.impl;

import com.rentacar.agentbackend.domain.PickupSpot;
import com.rentacar.agentbackend.repository.PickupSpotRepository;
import com.rentacar.agentbackend.service.PickupSpotService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickupSpotServiceImpl implements PickupSpotService {

    private final PickupSpotRepository pickupSpotRepository;

    public PickupSpotServiceImpl(PickupSpotRepository pickupSpotRepository) {
        this.pickupSpotRepository = pickupSpotRepository;
    }

    @Override
    public boolean exists(Long id) {
        return this.pickupSpotRepository.existsById(id);
    }

    @Override
    public PickupSpot findById(Long id) {
        return this.pickupSpotRepository.findById(id).orElse(null);
    }

    @Override
    public List<PickupSpot> getAll() {
        return this.pickupSpotRepository.findAll();
    }
}
