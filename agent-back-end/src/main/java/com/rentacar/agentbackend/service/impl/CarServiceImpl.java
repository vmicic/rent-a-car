package com.rentacar.agentbackend.service.impl;

import java.io.IOException;
import java.util.List;

import com.rentacar.agentbackend.repository.ImageRepository;
import com.rentacar.agentbackend.service.CarBrandService;
import com.rentacar.agentbackend.service.CarClassService;
import com.rentacar.agentbackend.service.CarModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rentacar.agentbackend.domain.Car;
import com.rentacar.agentbackend.domain.CarBrand;
import com.rentacar.agentbackend.domain.CarClass;
import com.rentacar.agentbackend.domain.CarModel;
import com.rentacar.agentbackend.domain.FuelType;
import com.rentacar.agentbackend.domain.Image;
import com.rentacar.agentbackend.domain.Rating;
import com.rentacar.agentbackend.domain.TransmissionType;
import com.rentacar.agentbackend.domain.User;
import com.rentacar.agentbackend.domain.dto.CarDTO;
import com.rentacar.agentbackend.repository.CarRepository;
import com.rentacar.agentbackend.repository.UserRepository;
import com.rentacar.agentbackend.service.CarService;
import com.rentacar.agentbackend.service.FuelTypeService;
import com.rentacar.agentbackend.service.TransmissionTypeService;
import com.rentacar.agentbackend.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class CarServiceImpl implements CarService {

	private static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

	private final CarRepository carRepository;
	private final ImageRepository imageRepository;
	private final UserRepository userRepository;

	private final CarModelService carModelService;
	private final CarBrandService carBrandService;
	private final FuelTypeService fuelTypeService;
	private final TransmissionTypeService transmissionTypeService;
	private final CarClassService carClassService;
	private final UserService userService;
	
	
	public CarServiceImpl(CarRepository carRepository, ImageRepository imageRepository, UserRepository userRepository,
			CarModelService carModelService, CarBrandService carBrandService, FuelTypeService fuelTypeService,
			TransmissionTypeService transmissionTypeService, CarClassService carClassService, UserService userService) {
		this.carRepository = carRepository;
		this.imageRepository = imageRepository;
		this.userRepository = userRepository;
		this.carModelService = carModelService;
		this.carBrandService = carBrandService;
		this.fuelTypeService = fuelTypeService;
		this.transmissionTypeService = transmissionTypeService;
		this.carClassService = carClassService;
		this.userService = userService;
	}
	
	@Override
	public Car create(CarDTO carDTO, User creator) {

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

		newCar.setUser(creator);

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
	@Transactional
	public List<Car> findAllByUser(User user) {
		return this.carRepository.findAllByUserEquals(user);
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
	public boolean loggedInUserOwner(Car car, User user) {
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

	@Override
	//check if user already posted rating for selected car
	public boolean carAlreadyRated(Long carId, User user) {
		Car car = this.findById(carId);
		
		for(Rating rating : car.getRatings()) {
			if(rating.getRatingPosted().getId().equals(user.getId())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public List<Image> getAllImages(Long id) {
		Car car = this.findById(id);

		return car.getImage();
	}
}
