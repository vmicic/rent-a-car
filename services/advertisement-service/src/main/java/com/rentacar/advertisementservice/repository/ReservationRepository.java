package com.rentacar.advertisementservice.repository;

import com.rentacar.advertisementservice.domain.Car;
import com.rentacar.advertisementservice.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByCarsContains(Car car);
}
