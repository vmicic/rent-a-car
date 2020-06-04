package com.rentacar.advertisementservice.service;

import com.rentacar.advertisementservice.domain.PickupSpot;

public interface PickupSpotService {

    boolean exists(Long id);

    PickupSpot findById(Long id);
}
