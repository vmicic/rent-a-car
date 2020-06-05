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
                if(checkIfTimeIsBetween(fromTime, reservation.getFromDate(), reservation.getToDate())) {
                    logger.info("reservation.start <= fromTime <= reservation.end");
                    conflict = true;
                    break;
                }

                if(checkIfTimeIsBetween(toTime, reservation.getFromDate(), reservation.getToDate())) {
                    logger.info("reservation.start <= toTime <= reservation.end");
                    conflict = true;
                    break;
                }

                //if reservation completely overlaps another
                if((fromTime.isBefore(reservation.getFromDate()) || fromTime.isEqual(reservation.getToDate())) &&
                        (toTime.isAfter(reservation.getFromDate()) || toTime.isEqual(reservation.getToDate()))) {
                    logger.info("overlaps");
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

    private boolean checkIfTimeIsBetween(LocalDateTime checkFor, LocalDateTime startTime, LocalDateTime endTime) {
        return (checkFor.isAfter(startTime) || checkFor.isEqual(startTime)) &&
                (checkFor.isBefore(endTime) || checkFor.isEqual(endTime));
    }
}
