package com.rentacar.advertisementservice.repository;

import com.rentacar.advertisementservice.domain.Rating;
import com.rentacar.advertisementservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rentacar.advertisementservice.domain.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long>{

    List<Car> findAllByUserEquals(User user);

}
