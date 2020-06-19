package com.rentacar.agentbackend.service;

import java.time.LocalDateTime;
import java.util.List;

import com.rentacar.agentbackend.domain.Advertisement;
import com.rentacar.agentbackend.domain.User;
import com.rentacar.agentbackend.domain.dto.AdvertisementDTO;

public interface AdvertisementService {
	
	Advertisement createAdvertisement(AdvertisementDTO advertisementDTO, User creator);
	
	void deleteAdvertisement(Long id);

    List<Advertisement> getAllAdvertisements();

    Advertisement findById(Long id);

    boolean exists(Long id);

    Integer getNumberOfCreatedAdvertisements(User user);
    
    List<Advertisement> findAdvertisementInRequestedDate(LocalDateTime fromTime, LocalDateTime toTime, Long pickupSpotId);
}
