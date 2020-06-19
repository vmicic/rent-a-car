package com.rentacar.agentbackend.service;

import java.io.IOException;
import java.util.List;

import com.rentacar.agentbackend.domain.Car;
import com.rentacar.agentbackend.domain.Image;
import com.rentacar.agentbackend.domain.User;
import com.rentacar.agentbackend.domain.dto.CarDTO;
import org.springframework.web.multipart.MultipartFile;

public interface CarService {
	
	Car create(CarDTO carDTO, User creator);
	
	List<Car> findAll();
	
	List<Car> findAllByUser(User user);
	
	Car findById(Long id);
	
	Car update(Long id, CarDTO carDTO);
	
	boolean delete(Long id);

    boolean exists(Long id);

    boolean loggedInUserOwner(Car car, User user);

    void saveImage(MultipartFile imageFile, Long id) throws IOException;

    boolean carAlreadyRated(Long carId, User user);

    List<Image> getAllImages(Long id);
}
