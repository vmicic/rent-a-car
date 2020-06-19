package com.rentacar.agentbackend.repository;

import com.rentacar.agentbackend.domain.PickupSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupSpotRepository extends JpaRepository<PickupSpot, Long> {
}
