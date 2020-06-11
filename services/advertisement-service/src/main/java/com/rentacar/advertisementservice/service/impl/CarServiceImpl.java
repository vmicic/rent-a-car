package com.rentacar.advertisementservice.service.impl;

import java.io.IOException;
import java.util.List;

import com.rentacar.advertisementservice.domain.CarBrand;
import com.rentacar.advertisementservice.domain.CarClass;
import com.rentacar.advertisementservice.repository.ImageRepository;
import com.rentacar.advertisementservice.service.CarBrandService;
import com.rentacar.advertisementservice.service.CarClassService;
import com.rentacar.advertisementservice.service.CarModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rentacar.advertisementservice.client.UserServiceClient;
import com.rentacar.advertisementservice.domain.Car;
import com.rentacar.advertisementservice.domain.CarModel;
import com.rentacar.advertisementservice.domain.FuelType;
import com.rentacar.advertisementservice.domain.Image;
import com.rentacar.advertisementservice.domain.TransmissionType;
import com.rentacar.advertisementservice.domain.User;
import com.rentacar.advertisementservice.domain.dto.CarDTO;
import com.rentacar.advertisementservice.repository.CarRepository;
import com.rentacar.advertisementservice.repository.UserRepository;
import com.rentacar.advertisementservice.service.CarService;
import com.rentacar.advertisementservice.service.FuelTypeService;
import com.rentacar.advertisementservice.service.TransmissionTypeService;
import com.rentacar.advertisementservice.service.UserService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CarServiceImpl implements CarService {

	private static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

	private final CarRepository carRepository;
	private final ImageRepository imageRepository;
	private final UserRepository userRepository;
	
	private final UserServiceClient userServiceClient;

	private final CarModelService carModelService;
	private final CarBrandService carBrandService;
	private final FuelTypeService fuelTypeService;
	private final TransmissionTypeService transmissionTypeService;
	private final CarClassService carClassService;
	private final UserService userService;
	
	
	public CarServiceImpl(CarRepository carRepository, ImageRepository imageRepository, UserRepository userRepository, UserServiceClient userServiceClient,
						  CarModelService carModelService, CarBrandService carBrandService, FuelTypeService fuelTypeService,
						  TransmissionTypeService transmissionTypeService, CarClassService carClassService, UserService userService) {
		this.carRepository = carRepository;
		this.imageRepository = imageRepository;
		this.userRepository = userRepository;
		this.userServiceClient = userServiceClient;
		this.carModelService = carModelService;
		this.carBrandService = carBrandService;
		this.fuelTypeService = fuelTypeService;
		this.transmissionTypeService = transmissionTypeService;
		this.carClassService = carClassService;
		this.userService = userService;
	}
	
	@Override
	public Car create(CarDTO carDTO) {

		CarModel carModel = carModelService.findById(carDTO.getCarModelId());
		CarBrand carBrand = carBrandService.findById(carDTO.getCarBrandId());
		FuelType fuelType = fuelTypeService.findById(carDTO.getFuelTypeId());
		TransmissionType transmissionType = transmissionTypeService.findById(carDTO.getTransmissionTypeId());
		CarClass carClass = carClassService.findById(carDTO.getCarClassId());
		
		if(carModel == null || carBrand == null || fuelType == null || transmissionType == null || carClass == null) {
			return null;
		}
		
		logger.info("Creating a car");

		Car newCar = new Car();
		
		newCar.setCarModel(carModel);
		newCar.setCarBrand(carBrand);
		newCar.setFuelType(fuelType);
		newCar.setTransmissionType(transmissionType);
		newCar.setCarClass(carClass);
		newCar.setKmTraveled(carDTO.getKmTraveled());
		newCar.setSeatsForKids(carDTO.getSeatsForKids());


		User owner = userServiceClient.getLoggedInUser();
		

		newCar.setUser(owner);

		//Company company = null; // TODO treba dodati company is request-a
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
		return this.carRepository.existsById(id);
	}

	@Override
	public boolean loggedInUserOwner(Car car) {
		User user = userServiceClient.getLoggedInUser();

		return car.getUser().equals(user);
	}

	@Override
	public void saveImage(MultipartFile imageFile, Long id) throws IOException {
		byte[] bytes = imageFile.getBytes();

		Car car = this.findById(id);

		Image image = new Image();
		image.setData(bytes);
		image.setCar(car);



		this.imageRepository.save(image);

	}
}
