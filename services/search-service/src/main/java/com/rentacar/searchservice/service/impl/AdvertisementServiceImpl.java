package com.rentacar.searchservice.service.impl;

import com.rentacar.searchservice.domain.Advertisement;
import com.rentacar.searchservice.repository.AdvertisementRepository;
import com.rentacar.searchservice.service.AdvertisementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private static Logger logger = LoggerFactory.getLogger(AdvertisementServiceImpl.class);

    private final AdvertisementRepository advertisementRepository;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public List<Advertisement> findAdvertisementInRequestedDate(LocalDateTime fromTime, LocalDateTime toTime) {
        logger.info("Searching advertisement between: " + fromTime.toString() + " end: " + toTime.toString());
        return this.advertisementRepository.findAllByFromDateIsLessThanEqualAndToDateGreaterThanEqual(fromTime, toTime);
    }
}
