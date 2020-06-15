package com.rentacar.advertisementservice.service;

import java.io.IOException;
import java.util.List;

import com.rentacar.advertisementservice.domain.Car;
import com.rentacar.advertisementservice.domain.Image;
import com.rentacar.advertisementservice.domain.dto.CarDTO;
import org.springframework.web.multipart.MultipartFile;

public interface CarService {
	
	Car create(CarDTO carDTO);
	
	List<Car> findAll();
	
	List<Car> findAllByUser();
	
	Car findById(Long id);
	
	Car update(Long id, CarDTO carDTO);
	
	boolean delete(Long id);

    boolean exists(Long id);

    boolean loggedInUserOwner(Car car);

    void saveImage(MultipartFile imageFile, Long id) throws IOException;

    boolean carAlreadyRated(Long id);

    List<Image> getAllImages(Long id);
}
