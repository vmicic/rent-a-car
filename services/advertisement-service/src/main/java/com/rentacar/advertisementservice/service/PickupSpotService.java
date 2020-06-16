package com.rentacar.advertisementservice.service;

import com.rentacar.advertisementservice.domain.PickupSpot;

import java.util.List;

public interface PickupSpotService {

    boolean exists(Long id);

    PickupSpot findById(Long id);

    List<PickupSpot> getAll();
}
