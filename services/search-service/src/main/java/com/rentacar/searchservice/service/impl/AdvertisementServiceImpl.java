package com.rentacar.searchservice.service.impl;

import com.rentacar.searchservice.domain.Advertisement;
import com.rentacar.searchservice.domain.PickupSpot;
import com.rentacar.searchservice.domain.Reservation;
import com.rentacar.searchservice.repository.AdvertisementRepository;
import com.rentacar.searchservice.service.AdvertisementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

        List<Advertisement> advertisementsAll = this.advertisementRepository.findAllByFromDateIsLessThanEqualAndToDateGreaterThanEqual(fromTime, toTime);

        List<Advertisement> advertisements = new ArrayList<>();

        for(Advertisement advertisement : advertisementsAll) {
            logger.info("For advertisement: " + advertisement.getId());
            logger.info("Searching period: " + fromTime.toString() + " end: " + toTime.toString());
            boolean conflict = false;

            for(Reservation reservation : advertisement.getReservations()) {
                logger.info("Reservation period: " + reservation.getFromDate() + " - " + reservation.getToDate());
                //compare if from or to time is between some reservation
                // reservation.start <= fromTime <= reservation.end
                if((fromTime.isAfter(reservation.getFromDate()) || fromTime.isEqual(reservation.getFromDate()) &&
                        fromTime.isBefore(reservation.getToDate()) || fromTime.isEqual(reservation.getToDate()))) {
                    conflict = true;
                    break;
                }

                if((toTime.isAfter(reservation.getFromDate()) || toTime.isEqual(reservation.getFromDate()) &&
                        toTime.isBefore(reservation.getToDate()) || toTime.isEqual(reservation.getToDate()))) {
                    conflict = true;
                    break;
                }
            }

            //if there isn't conflict add advertisement but if there is conflict continue to next advertisement
            if(!conflict) {
                advertisements.add(advertisement);
            } else {
                conflict = false;
            }
        }

        return advertisements;
    }

    @Override
    public List<Advertisement> findAdvertisementsForPickupSpot(List<Advertisement> advertisements, Long pickupSpotId) {
        List<Advertisement> validAdvertisements = new ArrayList<>();

        for(Advertisement advertisement : advertisements) {
            List<PickupSpot> pickupSpots = advertisement.getPickupSpots();
            for(PickupSpot pickupSpotAdv : pickupSpots) {
                if(pickupSpotAdv.getId().equals(pickupSpotId)) {
                    validAdvertisements.add(advertisement);
                    break;
                }
            }
        }

        return validAdvertisements;
    }
}
