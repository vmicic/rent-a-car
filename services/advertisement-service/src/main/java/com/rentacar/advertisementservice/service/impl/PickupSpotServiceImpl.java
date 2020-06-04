package com.rentacar.advertisementservice.service.impl;

import com.rentacar.advertisementservice.domain.PickupSpot;
import com.rentacar.advertisementservice.repository.PickupSpotRepository;
import com.rentacar.advertisementservice.service.PickupSpotService;
import org.springframework.stereotype.Service;

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
}
