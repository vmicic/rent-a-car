package com.rentacar.searchservice.service.impl;

import com.rentacar.searchservice.domain.Advertisement;
import com.rentacar.searchservice.domain.PickupSpot;
import com.rentacar.searchservice.repository.AdvertisementRepository;
import com.rentacar.searchservice.service.AdvertisementService;
import com.rentacar.searchservice.service.PickupSpotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private static final Logger logger = LoggerFactory.getLogger(AdvertisementServiceImpl.class);

    private final AdvertisementRepository advertisementRepository;
    private final PickupSpotService pickupSpotService;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository, PickupSpotService pickupSpotService) {
        this.advertisementRepository = advertisementRepository;
        this.pickupSpotService = pickupSpotService;
    }

    @Override
    public List<Advertisement> findAdvertisementInRequestedDate(LocalDateTime fromTime, LocalDateTime toTime, Long pickupSpotId) {

        PickupSpot pickupSpot = pickupSpotService.findById(pickupSpotId);

        return this.advertisementRepository.findAllByFromDateIsLessThanEqualAndToDateGreaterThanEqualAndPickupSpotsContains(fromTime, toTime, pickupSpot);

    }


    private boolean checkIfTimeIsBetween(LocalDateTime checkFor, LocalDateTime startTime, LocalDateTime endTime) {
        return (checkFor.isAfter(startTime) || checkFor.isEqual(startTime)) &&
                (checkFor.isBefore(endTime) || checkFor.isEqual(endTime));
    }
}
