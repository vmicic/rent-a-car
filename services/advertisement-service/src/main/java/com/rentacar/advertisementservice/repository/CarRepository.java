package com.rentacar.advertisementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentacar.advertisementservice.domain.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

}
