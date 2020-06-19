package com.rentacar.agentbackend.repository;

import com.rentacar.agentbackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentacar.agentbackend.domain.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

    List<Car> findAllByUserEquals(User user);

}
