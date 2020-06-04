package com.rentacar.advertisementservice.service.impl;

import com.rentacar.advertisementservice.client.UserServiceClient;
import com.rentacar.advertisementservice.domain.*;
import com.rentacar.advertisementservice.domain.dto.ReservationDTO;
import com.rentacar.advertisementservice.repository.ReservationRepository;
import com.rentacar.advertisementservice.service.AdvertisementService;
import com.rentacar.advertisementservice.service.CarService;
import com.rentacar.advertisementservice.service.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final AdvertisementService advertisementService;
    private final CarService carService;
    private final UserServiceClient userServiceClient;

    public ReservationServiceImpl(ReservationRepository reservationRepository, AdvertisementService advertisementService, CarService carService, UserServiceClient userServiceClient) {
        this.reservationRepository = reservationRepository;
        this.advertisementService = advertisementService;
        this.carService = carService;
        this.userServiceClient = userServiceClient;
    }

    @Override
    public Reservation createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();

        reservation.setFromDate(reservationDTO.getFromDate());
        reservation.setToDate(reservationDTO.getToDate());
        reservation.setCreationDateTime(LocalDateTime.now());
        reservation.setState(ReservationState.PENDING);

        Advertisement advertisement = advertisementService.findById(reservationDTO.getAdvertisementId());
        reservation.setAdvertisement(advertisement);

        List<Car> cars = new ArrayList<>();
        for(Long id : reservationDTO.getCarIds()) {
            Car car = this.carService.findById(id);
            cars.add(car);
        }

        reservation.setCars(cars);

        User user = userServiceClient.getLoggedInUser();
        reservation.setUser(user);

        return this.reservationRepository.save(reservation);
    }
}
