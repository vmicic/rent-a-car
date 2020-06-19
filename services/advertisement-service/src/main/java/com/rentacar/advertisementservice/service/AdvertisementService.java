package com.rentacar.advertisementservice.service;

import java.util.List;

import com.rentacar.advertisementservice.domain.Advertisement;
import com.rentacar.advertisementservice.domain.dto.AdvertisementDTO;
import com.rentacar.advertisementservice.domain.dto.ReservationDTO;

public interface AdvertisementService {
	
	Advertisement createAdvertisement(AdvertisementDTO advertisementDTO);
	
	void deleteAdvertisement(Long id);

    List<Advertisement> getAllAdvertisements();

    Advertisement findById(Long id);

    boolean exists(Long id);

    Integer getNumberOfCreatedAdvertisements();

    boolean advertisementForCarAndDateExists(ReservationDTO reservationDTO);
}
