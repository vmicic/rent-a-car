package com.rentacar.advertisementservice.service.impl;

import com.rentacar.advertisementservice.client.UserServiceClient;
import com.rentacar.advertisementservice.controller.ReservationController;
import com.rentacar.advertisementservice.domain.*;
import com.rentacar.advertisementservice.domain.dto.ReservationApprovedDTO;
import com.rentacar.advertisementservice.domain.dto.ReservationDTO;
import com.rentacar.advertisementservice.repository.ReservationRepository;
import com.rentacar.advertisementservice.service.AdvertisementService;
import com.rentacar.advertisementservice.service.CarService;
import com.rentacar.advertisementservice.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

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
    public boolean reservationPossible(ReservationDTO reservationDTO) {
        logger.info("Checking if reservation is possible for time: " + reservationDTO.getFromDate() + " - " + reservationDTO.getToDate());
        Advertisement advertisement = advertisementService.findById(reservationDTO.getAdvertisementId());

        boolean conflict = false;
        LocalDateTime fromDate = reservationDTO.getFromDate();
        LocalDateTime toDate = reservationDTO.getToDate();


        for(Reservation reservation : advertisement.getReservations()) {
            //compare if from or to time is between some reservation
            // reservation.start <= fromDate <= reservation.end
            if((fromDate.isAfter(reservation.getFromDate()) || fromDate.isEqual(reservation.getFromDate()) &&
                    fromDate.isBefore(reservation.getToDate()) || fromDate.isEqual(reservation.getToDate()))) {
                conflict = true;
                break;
            }

            // reservation.start <= endDate <= reservation.end
            if((toDate.isAfter(reservation.getFromDate()) || toDate.isEqual(reservation.getFromDate()) &&
                    toDate.isBefore(reservation.getToDate()) || toDate.isEqual(reservation.getToDate()))) {
                conflict = true;
                break;
            }
        }

        return !conflict;
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

    @Override
    public Reservation createApprovedReservation(ReservationApprovedDTO reservationApprovedDTO) {
        return null;
    }

    @Override
    public boolean reservationApprovedPossible(ReservationApprovedDTO reservationApprovedDTO) {


        return true;
    }


}
