package com.rentacar.advertisementservice.service.impl;

import com.rentacar.advertisementservice.client.UserServiceClient;
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

        LocalDateTime fromDate = reservationDTO.getFromDate();
        LocalDateTime toDate = reservationDTO.getToDate();

        for(Long id : reservationDTO.getCarIds()) {

            Car car = this.carService.findById(id);

            //checking if
            //reservation.start <= fromDate <= reservation.end
            //reservation.start <= endDate <= reservation.end
            if (!ifReservationPossibleForCar(fromDate, toDate, car)) {
                return false;
            }
        }

        return true;
    }

    private boolean ifReservationPossibleForCar(LocalDateTime fromDate, LocalDateTime toDate, Car car) {
        List<Reservation> reservations = this.reservationRepository.
                CarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStateEqualsOrAndCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStateEquals
                        (car, fromDate, fromDate, ReservationState.PAID, car, toDate, toDate, ReservationState.PAID);

        //checking if
        //reservation.start <= fromDate <= reservation.end
        //reservation.start <= endDate <= reservation.end
        if (reservations.size() > 0) {
            logger.info("Existing reservation which is already paid exists");
            return false;
        }

        //check if there is reservation which requested reservation overlaps
        List<Reservation> reservationInside = this.reservationRepository.
                CarsContainsAndFromDateGreaterThanEqualAndToDateLessThanEqualAndStateEquals(car, fromDate, toDate, ReservationState.PAID);

        if (reservationInside.size() > 0) {
            logger.info("Existing reservation inside requested examination exists");
            return false;
        }
        return true;
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
        for (Long id : reservationDTO.getCarIds()) {
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
        LocalDateTime fromDate = reservationApprovedDTO.getFromDate();
        LocalDateTime toDate = reservationApprovedDTO.getToDate();

        Reservation reservationNew = new Reservation();

        reservationNew.setFromDate(fromDate);
        reservationNew.setToDate(toDate);
        reservationNew.setCreationDateTime(LocalDateTime.now());
        reservationNew.setState(ReservationState.PAID);

        Car car = this.carService.findById(reservationApprovedDTO.getCarId());
        List<Car> cars = new ArrayList<>();

        cars.add(car);
        reservationNew.setCars(cars);

        List<Reservation> reservations = this.reservationRepository.findAllByCarsContains(car);

        for (Reservation reservation : reservations) {
            //compare if from or to time is between some reservation
            // reservation.start <= fromDate <= reservation.end
            if (checkIfTimeIsBetween(fromDate, reservation.getFromDate(), reservation.getToDate())) {
                //There is conflict if reservation is already paid, but if it's pending it will be canceled
                if (reservation.getState() == ReservationState.PENDING) {
                    logger.info("Canceling reservation " + reservation.getId());
                    this.cancelReservation(reservation.getId());
                }
            }

            // reservation.start <= toDate <= reservation.end
            if (checkIfTimeIsBetween(toDate, reservation.getFromDate(), reservation.getToDate())) {
                if (reservation.getState() == ReservationState.PENDING) {
                    logger.info("Canceling reservation " + reservation.getId());
                    this.cancelReservation(reservation.getId());
                }
            }
        }

        return this.reservationRepository.save(reservationNew);
    }

    @Override
    public boolean reservationApprovedPossible(ReservationApprovedDTO reservationApprovedDTO) {

        Car car = this.carService.findById(reservationApprovedDTO.getCarId());
        LocalDateTime fromDate = reservationApprovedDTO.getFromDate();
        LocalDateTime toDate = reservationApprovedDTO.getToDate();



        return ifReservationPossibleForCar(fromDate, toDate, car);
    }

    @Override
    public void cancelReservation(Long id) {
        Reservation reservation = this.reservationRepository.findById(id).orElse(null);

        if (reservation != null) {
            reservation.setState(ReservationState.CANCELED);
            this.reservationRepository.save(reservation);
        }
    }

    private boolean checkIfTimeIsBetween(LocalDateTime checkFor, LocalDateTime startTime, LocalDateTime endTime) {
        return (checkFor.isAfter(startTime) || checkFor.isEqual(startTime)) &&
                (checkFor.isBefore(endTime) || checkFor.isEqual(endTime));
    }
}
