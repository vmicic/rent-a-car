package com.rentacar.agentbackend.service;

import com.rentacar.agentbackend.domain.PickupSpot;

import java.util.List;

public interface PickupSpotService {

    boolean exists(Long id);

    PickupSpot findById(Long id);

    List<PickupSpot> getAll();
}
