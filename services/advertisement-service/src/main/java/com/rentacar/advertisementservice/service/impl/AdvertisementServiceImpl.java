package com.rentacar.advertisementservice.service.impl;

import com.rentacar.advertisementservice.client.UserServiceClient;
import com.rentacar.advertisementservice.domain.Advertisement;
import com.rentacar.advertisementservice.domain.Car;
import com.rentacar.advertisementservice.domain.PickupSpot;
import com.rentacar.advertisementservice.domain.User;
import com.rentacar.advertisementservice.domain.dto.AdvertisementDTO;
import com.rentacar.advertisementservice.domain.dto.ReservationDTO;
import com.rentacar.advertisementservice.repository.AdvertisementRepository;
import com.rentacar.advertisementservice.service.AdvertisementService;
import com.rentacar.advertisementservice.service.CarService;
import com.rentacar.advertisementservice.service.PickupSpotService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final PickupSpotService pickupSpotService;
    private final CarService carService;
    private final UserServiceClient userServiceClient;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository, PickupSpotService pickupSpotService, CarService carService, UserServiceClient userServiceClient) {
        this.advertisementRepository = advertisementRepository;
        this.pickupSpotService = pickupSpotService;
        this.carService = carService;
        this.userServiceClient = userServiceClient;
    }

    @Override
    public Advertisement createAdvertisement(AdvertisementDTO advertisementDTO) {
        Advertisement advertisement = new Advertisement();

        advertisement.setFromDate(advertisementDTO.getFromDate());
        advertisement.setToDate(advertisementDTO.getToDate());

        List<PickupSpot> pickupSpots = new ArrayList<>();

        for(Long id : advertisementDTO.getPickupSpots()) {
            PickupSpot pickupSpot = pickupSpotService.findById(id);
            pickupSpots.add(pickupSpot);
        }
        advertisement.setPickupSpots(pickupSpots);

        User user = userServiceClient.getLoggedInUser();
        advertisement.setUser(user);

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
    public Integer getNumberOfCreatedAdvertisements() {
        User user = userServiceClient.getLoggedInUser();

        return this.advertisementRepository.findAllByUserId(user.getId()).size();
    }

    @Override
    public boolean advertisementForCarAndDateExists(ReservationDTO reservationDTO) {


        for(Long id : reservationDTO.getCarIds()) {
            Car car = this.carService.findById(id);

            if(!this.advertisementRepository.existsByCarEqualsAndFromDateLessThanEqualAndToDateGreaterThanEqual
                    (car, reservationDTO.getFromDate(), reservationDTO.getToDate())) {
                return false;
            }
        }

        return true;
    }
}
