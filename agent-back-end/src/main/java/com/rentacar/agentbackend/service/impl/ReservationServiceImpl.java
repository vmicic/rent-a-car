package com.rentacar.agentbackend.service.impl;

import com.rentacar.agentbackend.domain.Advertisement;
import com.rentacar.agentbackend.domain.Car;
import com.rentacar.agentbackend.domain.Reservation;
import com.rentacar.agentbackend.domain.ReservationState;
import com.rentacar.agentbackend.domain.User;
import com.rentacar.agentbackend.domain.dto.RatingDTO;
import com.rentacar.agentbackend.domain.dto.ReservationApprovedDTO;
import com.rentacar.agentbackend.domain.dto.ReservationDTO;
import com.rentacar.agentbackend.repository.ReservationRepository;
import com.rentacar.agentbackend.service.AdvertisementService;
import com.rentacar.agentbackend.service.CarService;
import com.rentacar.agentbackend.service.ReservationService;
import com.rentacar.agentbackend.service.UserService;
import com.rentacar.agentbackend.service.scheduledTask.CancelReservationTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final ReservationRepository reservationRepository;
    private final AdvertisementService advertisementService;
    private final CarService carService;
    private final UserService userService;

    public ReservationServiceImpl(ReservationRepository reservationRepository, AdvertisementService advertisementService, CarService carService, 
    		UserService userService) {
        this.reservationRepository = reservationRepository;
        this.advertisementService = advertisementService;
        this.carService = carService;
        this.userService = userService;
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
    public Reservation createReservation(ReservationDTO reservationDTO, User creator) {
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
            reservation.setUserOwnerCar(car.getUser());
        }

        reservation.setCars(cars);

        reservation.setUser(creator);

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


    @Override
    public Reservation findById(Long id) {
        return this.reservationRepository.findById(id).orElse(null);
    }

    @Override
    public boolean exists(Long id) {
        return this.reservationRepository.existsById(id);
    }

    @Override
    public boolean loggedUserOwnerCar(Long id, User loggedInUser) {
        Reservation reservation = this.findById(id);

        return loggedInUser.equals(reservation.getUserOwnerCar());
    }

    @Override
    public void approveReservation(Long id) {
        Reservation reservation = this.findById(id);

        reservation.setState(ReservationState.RESERVED);

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
    public boolean canUsersExchangeMessages(Long id, User loggedInUser) {

        logger.info("Logged user is: " + loggedInUser.toString());
        logger.info("Checking if can exchange messages with: " + id);

        List<Reservation> reservations = this.reservationRepository.findAllByUserEqualsAndUserOwnerCar_IdAndStateEqualsOrUser_IdAndUserOwnerCarEqualsAndStateEquals(
        		loggedInUser, id, ReservationState.PAID, id, loggedInUser, ReservationState.PAID);

        logger.info("Reservations size: " + reservations.size());

        return !reservations.isEmpty();
    }

    @Override
    public boolean existsForRating(RatingDTO ratingDTO, User loggedInUser) {
        Car car = carService.findById(ratingDTO.getCarId());

        return this.reservationRepository.existsReservationByUserEqualsAndCarsContainsAndToDateBeforeAndStateEquals(
        		loggedInUser, car, LocalDateTime.now(), ReservationState.PAID);
    }
    
    @Override
    public boolean carPossibleToReserveForDate(Car car, LocalDateTime dateFrom, LocalDateTime dateTo) {
        boolean overlaps = this.reservationRepository.existsByCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStateEqualsOrCarsContainsAndFromDateLessThanEqualAndToDateGreaterThanEqualAndStateEquals(
                car, dateFrom, dateFrom, ReservationState.PAID, car, dateTo, dateTo, ReservationState.PAID);

        boolean inside = this.reservationRepository.existsByCarsContainsAndFromDateGreaterThanEqualAndToDateLessThanEqualAndStateEquals(car, dateFrom, dateTo, ReservationState.PAID);

        logger.info("Car id: " + car.getId() + ", overlaps: " + overlaps + ", inside: " + inside);

        return !(overlaps || inside);
    }
}
