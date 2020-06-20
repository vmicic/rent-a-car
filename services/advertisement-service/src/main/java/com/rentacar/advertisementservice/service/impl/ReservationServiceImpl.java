package com.rentacar.advertisementservice.service.impl;

import com.rentacar.advertisementservice.client.UserServiceClient;
import com.rentacar.advertisementservice.domain.*;
import com.rentacar.advertisementservice.domain.dto.RatingDTO;
import com.rentacar.advertisementservice.domain.dto.ReservationApprovedDTO;
import com.rentacar.advertisementservice.domain.dto.ReservationDTO;
import com.rentacar.advertisementservice.repository.ReservationRepository;
import com.rentacar.advertisementservice.service.AdvertisementService;
import com.rentacar.advertisementservice.service.CarService;
import com.rentacar.advertisementservice.service.ReservationService;
import com.rentacar.advertisementservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final ReservationRepository reservationRepository;
    private final AdvertisementService advertisementService;
    private final CarService carService;
    private final UserServiceClient userServiceClient;
    private final UserService userService;

    public ReservationServiceImpl(ReservationRepository reservationRepository, AdvertisementService advertisementService, CarService carService, UserServiceClient userServiceClient, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.advertisementService = advertisementService;
        this.carService = carService;
        this.userServiceClient = userServiceClient;
        this.userService = userService;
    }

    @Override
    public boolean reservationPossible(ReservationDTO reservationDTO) {

        LocalDateTime fromDate = reservationDTO.getFromDate();
        LocalDateTime toDate = reservationDTO.getToDate();

        for(Long id : reservationDTO.getCarIds()) {

            Car car = this.carService.findById(id);

            if (!ifReservationPossibleForCar(fromDate, toDate, car)) {
                return false;
            }
        }

        return true;
    }

    private boolean ifReservationPossibleForCar(LocalDateTime fromDate, LocalDateTime toDate, Car car) {
        List<Reservation> reservations = this.reservationRepository.
                CarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStateEqualsOrCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStateEquals
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

        List<Car> cars = new ArrayList<>();
        for (Long id : reservationDTO.getCarIds()) {
            Car car = this.carService.findById(id);
            cars.add(car);
            reservation.setUserOwnerCar(car.getUser());
        }

        reservation.setCars(cars);

        User user = userServiceClient.getLoggedInUser();
        reservation.setUser(user);

        return this.reservationRepository.save(reservation);
    }

    @Override
    public Reservation createApprovedReservation(ReservationApprovedDTO reservationApprovedDTO) {
        //TODO Implement canceling of reservation inside
        LocalDateTime fromDate = reservationApprovedDTO.getFromDate();
        LocalDateTime toDate = reservationApprovedDTO.getToDate();

        Reservation reservation = new Reservation();

        reservation.setFromDate(fromDate);
        reservation.setToDate(toDate);
        reservation.setCreationDateTime(LocalDateTime.now());
        reservation.setState(ReservationState.PAID);

        Car car = this.carService.findById(reservationApprovedDTO.getCarId());

        List<Car> cars = new ArrayList<>();
        cars.add(car);

        reservation.setCars(cars);

        cancelAllReservationsConflict(reservation);

        return this.reservationRepository.save(reservation);
    }

    private void cancelAllReservationsConflict(Reservation reservation) {
        for(Car car : reservation.getCars()) {
            List<Reservation> reservations = this.reservationRepository.
                    CarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStateEqualsOrCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStateEquals
                            (car, reservation.getFromDate(), reservation.getFromDate(), ReservationState.PENDING, car, reservation.getToDate(), reservation.getToDate(), ReservationState.PENDING);

            for(Reservation reservationToCancel : reservations) {
                if(reservationToCancel.equals(reservation)) {
                    logger.info("Found reservation with im trying to approve, skipping");
                    continue;
                }
                reservationToCancel.setState(ReservationState.CANCELED);
                this.reservationRepository.save(reservationToCancel);
            }

            List<Reservation> reservationInside = this.reservationRepository.
                    CarsContainsAndFromDateGreaterThanEqualAndToDateLessThanEqualAndStateEquals(car, reservation.getFromDate(), reservation.getToDate(), ReservationState.PENDING);

            for(Reservation reservationToCancel: reservationInside) {
                reservationToCancel.setState(ReservationState.CANCELED);
                this.reservationRepository.save(reservationToCancel);
            }
        }
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

    @Override
    public Reservation findById(Long id) {
        return this.reservationRepository.findById(id).orElse(null);
    }

    @Override
    public boolean exists(Long id) {
        return this.reservationRepository.existsById(id);
    }

    @Override
    public boolean loggedUserOwnerCar(Long id) {
        User user = userServiceClient.getLoggedInUser();

        Reservation reservation = this.findById(id);

        return user.equals(reservation.getUserOwnerCar());
    }

    @Override
    public void approveReservation(Long id) {
        Reservation reservation = this.findById(id);

        reservation.setState(ReservationState.PAID);

        cancelAllReservationsConflict(reservation);

        this.reservationRepository.save(reservation);
    }

    @Override
    //find all reservations older then some time and with state pending and cancel them
    public void cancelReservationOlderThen(LocalDateTime time) {
        List<Reservation> reservations = this.reservationRepository.findAllByCreationDateTimeBeforeAndStateEquals(time, ReservationState.PENDING);

        for(Reservation reservation : reservations) {
            reservation.setState(ReservationState.CANCELED);
            this.reservationRepository.save(reservation);
        }
    }

    @Override
    //check if there is reservations with logged and requested user with state reserved and then they can
    //exchange messages
    public boolean canUsersExchangeMessages(Long id) {
        User user = userServiceClient.getLoggedInUser();
        User user1 = userService.findById(id);

        logger.info("Logged user is: " + user.toString());
        logger.info("Checking if can exchange messages with: " + id);

        List<Reservation> reservations = this.reservationRepository.findAllByUserEqualsAndUserOwnerCar_IdAndStateEqualsOrUser_IdAndUserOwnerCarEqualsAndStateEquals(
                user, id, ReservationState.PAID, id, user, ReservationState.PAID);

        logger.info("Reservations size: " + reservations.size());

        return !reservations.isEmpty();
    }

    @Override
    public boolean existsForRating(RatingDTO ratingDTO) {
        User user = userServiceClient.getLoggedInUser();
        Car car = carService.findById(ratingDTO.getCarId());

        return this.reservationRepository.existsReservationByUserEqualsAndCarsContainsAndToDateBeforeAndStateEquals(
                user, car, LocalDateTime.now(), ReservationState.PAID);
    }

    @Override
    public List<Reservation> getReservationsForApproval() {
        User user = userServiceClient.getLoggedInUser();
        return this.reservationRepository.findAllByUserOwnerCarEqualsAndStateEquals(user, ReservationState.PENDING);
    }
}
