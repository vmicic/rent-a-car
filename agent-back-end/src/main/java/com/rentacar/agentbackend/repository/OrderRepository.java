package com.rentacar.agentbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentacar.agentbackend.domain.Reservation;

@Repository
public interface OrderRepository extends JpaRepository<Reservation, Long> {

}
