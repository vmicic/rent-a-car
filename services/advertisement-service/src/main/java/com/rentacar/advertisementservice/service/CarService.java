package com.rentacar.advertisementservice.service;

import java.util.List;

import com.rentacar.advertisementservice.domain.Car;
import com.rentacar.advertisementservice.domain.dto.CarDTO;

public interface CarService {
	
	Car create(CarDTO carDTO);
	
	List<Car> findAll();
	
	List<Car> findAllByUser();
	
	Car findById(Long id);
	
	Car update(Long id, CarDTO carDTO);
	
	boolean delete(Long id);

    boolean exists(Long id);
}
