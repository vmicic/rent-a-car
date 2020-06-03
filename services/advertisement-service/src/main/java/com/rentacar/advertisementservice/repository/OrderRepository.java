package com.rentacar.advertisementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentacar.advertisementservice.domain.Reservation;

public interface OrderRepository extends JpaRepository<Reservation, Long> {

}
