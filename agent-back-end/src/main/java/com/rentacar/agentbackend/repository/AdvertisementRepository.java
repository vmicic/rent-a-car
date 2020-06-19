package com.rentacar.agentbackend.repository;

import com.rentacar.agentbackend.domain.Advertisement;
import com.rentacar.agentbackend.domain.PickupSpot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>{

    List<Advertisement> findAllByUserId(Long id);

    List<Advertisement> findAllByFromDateIsLessThanEqualAndToDateGreaterThanEqualAndPickupSpotsContains(LocalDateTime fromTime, LocalDateTime toTime, PickupSpot pickupSpot);
}
