package com.rentacar.advertisementservice.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rentacar.advertisementservice.client.AdministratorServiceClient;
import com.rentacar.advertisementservice.domain.Car;
import com.rentacar.advertisementservice.domain.CarBrand;
import com.rentacar.advertisementservice.domain.CarClass;
import com.rentacar.advertisementservice.domain.CarModel;
import com.rentacar.advertisementservice.domain.Company;
import com.rentacar.advertisementservice.domain.FuelType;
import com.rentacar.advertisementservice.domain.TransmissionType;
import com.rentacar.advertisementservice.domain.User;
import com.rentacar.advertisementservice.domain.dto.CarDTO;
import com.rentacar.advertisementservice.repository.CarRepository;
import com.rentacar.advertisementservice.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	private static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

	private final CarRepository carRepository;
	private final AdministratorServiceClient administratorServiceClient;
	
	public CarServiceImpl(CarRepository carRepository, AdministratorServiceClient administratorServiceClient) {
		this.carRepository = carRepository;
		this.administratorServiceClient = administratorServiceClient;
	}
	
	@Override
	public Car create(CarDTO carDTO) {
		
		Car newCar = new Car();
		logger.info("Creating a car");
		CarModel carModel = administratorServiceClient.getModel(carDTO.getCarModel());
		CarBrand carBrand = administratorServiceClient.getBrand(carDTO.getCarBrand());
		FuelType fuelType = administratorServiceClient.getFuelType(carDTO.getFuelType());
		TransmissionType transmissionType = administratorServiceClient.getTransmissionType(carDTO.getTransmissionType());
		CarClass carClass = administratorServiceClient.getCarClass(carDTO.getCarClass());
		User user = null; // TODO treba dodati usera iz request-a
		Company company = null; // TODO treba dodati company is request-a
		
		/* TODO
		 * Treba ubaciti jos sve moguce provere da li svi gore navedeni postoje
		 */
		
		newCar.setCarModel(carModel);
		newCar.setCarBrand(carBrand);
		newCar.setFuelType(fuelType);
		newCar.setTransmissionType(transmissionType);
		newCar.setCarClass(carClass);
		//newCar.setKmTraveled(carDTO.getKmTraveled());
		newCar.setSeatsForKids(carDTO.getSeatsForKids());
		//newCar.setUser(user);
		//newCar.setCompany(company);
		
		return carRepository.save(newCar);
	}

	@Override
	public List<Car> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car> findAllByUser() {
		return null;
	}

	@Override
	public Car findById(Long id) {
		return carRepository.findById(id).orElse(null);
	}

	@Override
	public Car update(Long id, CarDTO carDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
