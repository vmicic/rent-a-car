package com.rentacar.searchservice.service;

import com.rentacar.searchservice.domain.Advertisement;

import java.time.LocalDateTime;
import java.util.List;

public interface AdvertisementService {

    List<Advertisement> findAdvertisementInRequestedDate(LocalDateTime fromTime, LocalDateTime toTime);
}
