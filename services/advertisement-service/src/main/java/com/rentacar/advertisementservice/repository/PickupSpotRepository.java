package com.rentacar.advertisementservice.repository;

import com.rentacar.advertisementservice.domain.PickupSpot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickupSpotRepository extends JpaRepository<PickupSpot, Long> {
}
