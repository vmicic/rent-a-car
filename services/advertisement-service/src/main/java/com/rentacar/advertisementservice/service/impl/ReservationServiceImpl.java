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
        logger.info("Checking if reservation is possible for time: " + reservationDTO.getFromDate() + " - " + reservationDTO.getToDate());
        Advertisement advertisement = advertisementService.findById(reservationDTO.getAdvertisementId());

        LocalDateTime fromDate = reservationDTO.getFromDate();
        LocalDateTime toDate = reservationDTO.getToDate();

        //Check if reservation overlaps from some reservation from advertisement
        for (Reservation reservation : advertisement.getReservations()) {
            //compare if from or to time is between some reservation
            // reservation.start <= fromDate <= reservation.end
            if ((fromDate.isAfter(reservation.getFromDate()) || fromDate.isEqual(reservation.getFromDate()) &&
                    fromDate.isBefore(reservation.getToDate()) || fromDate.isEqual(reservation.getToDate()))) {
                if(reservation.getState() == ReservationState.PAID) {
                    return false;
                }
            }

            // reservation.start <= endDate <= reservation.end
            if ((toDate.isAfter(reservation.getFromDate()) || toDate.isEqual(reservation.getFromDate()) &&
                    toDate.isBefore(reservation.getToDate()) || toDate.isEqual(reservation.getToDate()))) {
                if(reservation.getState() == ReservationState.PAID) {
                    return false;
                }
            }
        }


        //check for manuel created reservations which time overlaps with wanted reservation
        // check if reservationManual.start <= fromDate <= reservationManual.end or reservationManual.start <= toDate <= reservationManual.end
        // advertisement is null means reservation is manually created and cars contains means to check only for reservation for specific car
        for(Long id : reservationDTO.getCarIds()) {
            Car car = this.carService.findById(id);
            System.out.println("Trying to reserve for date: " + fromDate + " - " + toDate);
            List<Reservation> reservationsManual = this.reservationRepository.
                    findAllByAdvertisementNullAndCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualOrAdvertisementNullAndCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqual
                            (car, fromDate, fromDate, car, toDate, toDate);

            if(reservationsManual.size() > 0) {
                logger.info("Reservation is inside another already approved reservation");
                return false;
            }

            //check for reservation which are completely inside requested reservation
            List<Reservation> reservationsManualInside = this.reservationRepository.
                    findAllByAdvertisementNullAndCarsContainsAndFromDateGreaterThanEqualAndToDateLessThanEqual(car, fromDate, toDate);

            if(reservationsManualInside.size() > 0) {
                logger.info("Some reservation is completely inside requested reservation");
                return false;
            }
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
            if ((fromDate.isAfter(reservation.getFromDate()) || fromDate.isEqual(reservation.getFromDate()) &&
                    fromDate.isBefore(reservation.getToDate()) || fromDate.isEqual(reservation.getToDate()))) {
                //There is conflict if reservation is already paid, but if it's pending it will be canceled
                if (reservation.getState() == ReservationState.PENDING) {
                    logger.info("Canceling reservation " + reservation.getId());
                    this.cancelReservation(reservation.getId());
                }
            }

            // reservation.start <= endDate <= reservation.end
            if ((toDate.isAfter(reservation.getFromDate()) || toDate.isEqual(reservation.getFromDate()) &&
                    toDate.isBefore(reservation.getToDate()) || toDate.isEqual(reservation.getToDate()))) {
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

        List<Reservation> reservations = this.reservationRepository.findAllByCarsContains(car);

        boolean conflict = false;

        for (Reservation reservation : reservations) {
            //compare if from or to time is between some reservation
            // reservation.start <= fromDate <= reservation.end
            if ((fromDate.isAfter(reservation.getFromDate()) || fromDate.isEqual(reservation.getFromDate()) &&
                    fromDate.isBefore(reservation.getToDate()) || fromDate.isEqual(reservation.getToDate()))) {
                //There is conflict if reservation is already paid, but if it's pending it will be canceled
                if (reservation.getState() == ReservationState.PAID) {
                    conflict = true;
                    break;
                }
            }

            // reservation.start <= endDate <= reservation.end
            if ((toDate.isAfter(reservation.getFromDate()) || toDate.isEqual(reservation.getFromDate()) &&
                    toDate.isBefore(reservation.getToDate()) || toDate.isEqual(reservation.getToDate()))) {
                if (reservation.getState() == ReservationState.PAID) {
                    conflict = true;
                    break;
                }
            }
        }

        return !conflict;
    }

    @Override
    public void cancelReservation(Long id) {
        Reservation reservation = this.reservationRepository.findById(id).orElse(null);

        if (reservation != null) {
            reservation.setState(ReservationState.CANCELED);
            this.reservationRepository.save(reservation);
        }
    }
}
