package com.rentacar.searchservice.service;

import com.rentacar.searchservice.domain.Advertisement;
import com.rentacar.searchservice.domain.PickupSpot;

import java.time.LocalDateTime;
import java.util.List;

public interface AdvertisementService {

    List<Advertisement> findAdvertisementInRequestedDate(LocalDateTime fromTime, LocalDateTime toTime);

    List<Advertisement> findAdvertisementsForPickupSpot(List<Advertisement> advertisements, Long pickupSpotId);
}
