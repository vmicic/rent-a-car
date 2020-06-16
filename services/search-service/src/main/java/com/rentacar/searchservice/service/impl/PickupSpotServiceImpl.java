package com.rentacar.searchservice.service.impl;

import com.rentacar.searchservice.domain.PickupSpot;
import com.rentacar.searchservice.repository.PickupSpotRepository;
import com.rentacar.searchservice.service.PickupSpotService;
import org.springframework.stereotype.Service;

@Service
public class PickupSpotServiceImpl implements PickupSpotService {

    private final PickupSpotRepository pickupSpotRepository;

    public PickupSpotServiceImpl(PickupSpotRepository pickupSpotRepository) {
        this.pickupSpotRepository = pickupSpotRepository;
    }

    @Override
    public PickupSpot findById(Long id) {
        return this.pickupSpotRepository.findById(id).orElse(null);
    }
}
