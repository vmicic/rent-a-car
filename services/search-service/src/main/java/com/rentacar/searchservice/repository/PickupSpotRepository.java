package com.rentacar.searchservice.repository;

import com.rentacar.searchservice.domain.PickupSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupSpotRepository  extends JpaRepository<PickupSpot, Long> {
}
