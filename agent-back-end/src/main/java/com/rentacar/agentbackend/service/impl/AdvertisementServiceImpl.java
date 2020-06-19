package com.rentacar.agentbackend.service.impl;

import com.rentacar.agentbackend.domain.Advertisement;
import com.rentacar.agentbackend.domain.Car;
import com.rentacar.agentbackend.domain.PickupSpot;
import com.rentacar.agentbackend.domain.User;
import com.rentacar.agentbackend.domain.dto.AdvertisementDTO;
import com.rentacar.agentbackend.repository.AdvertisementRepository;
import com.rentacar.agentbackend.service.AdvertisementService;
import com.rentacar.agentbackend.service.CarService;
import com.rentacar.agentbackend.service.PickupSpotService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final PickupSpotService pickupSpotService;
    private final CarService carService;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository, PickupSpotService pickupSpotService, CarService carService) {
        this.advertisementRepository = advertisementRepository;
        this.pickupSpotService = pickupSpotService;
        this.carService = carService;
    }

    @Override
    public Advertisement createAdvertisement(AdvertisementDTO advertisementDTO, User creator) {
        Advertisement advertisement = new Advertisement();

        advertisement.setFromDate(advertisementDTO.getFromDate());
        advertisement.setToDate(advertisementDTO.getToDate());

        List<PickupSpot> pickupSpots = new ArrayList<>();

        for(Long id : advertisementDTO.getPickupSpots()) {
            PickupSpot pickupSpot = pickupSpotService.findById(id);
            pickupSpots.add(pickupSpot);
        }
        advertisement.setPickupSpots(pickupSpots);

        advertisement.setUser(creator);

        Car car = carService.findById(advertisementDTO.getCarId());
        advertisement.setCar(car);

        return this.advertisementRepository.save(advertisement);
    }

    @Override
    public void deleteAdvertisement(Long id) {

    }

    @Override
    public List<Advertisement> getAllAdvertisements() {
        return null;
    }

    @Override
    public Advertisement findById(Long id) {
        return this.advertisementRepository.findById(id).orElse(null);
    }

    @Override
    public boolean exists(Long id) {
        return this.advertisementRepository.existsById(id);
    }

    @Override
    public Integer getNumberOfCreatedAdvertisements(User user) {

        return this.advertisementRepository.findAllByUserId(user.getId()).size();
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
