package com.rentacar.advertisementservice.service;

import java.util.List;

import com.rentacar.advertisementservice.domain.Car;
import com.rentacar.advertisementservice.domain.dto.CarDTO;

public interface CarService {
	
	Car createCar(CarDTO carDTO);
	
	void deleteCar(Long id);

    List<Car> getAllCars();
}
