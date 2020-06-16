package com.rentacar.searchservice.controller;

import com.rentacar.searchservice.domain.Advertisement;
import com.rentacar.searchservice.domain.Car;
import com.rentacar.searchservice.service.AdvertisementService;
import com.rentacar.searchservice.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    private final AdvertisementService advertisementService;
    private final ReservationService reservationService;

    public SearchController(AdvertisementService advertisementService, ReservationService reservationService) {
        this.advertisementService = advertisementService;
        this.reservationService = reservationService;
    }


    @GetMapping("/search")
    public ResponseEntity<?> findCars(@RequestParam(value = "pickup", required = true) Long pickupSpotId,
                        @RequestParam(value = "dateFrom", required = true)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
                        @RequestParam(value = "dateTo", required = true)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo,
                        @RequestParam(value = "carBrand", required = false) String carBrand,
                        @RequestParam(value = "carModel", required = false) String carModel,
                        @RequestParam(value = "fuelType", required = false) String fuelType,
                        @RequestParam(value = "carClass", required = false) String carClass,
                        @RequestParam(value = "priceFrom", required = false) String priceFrom,
                        @RequestParam(value = "priceTo", required = false) String priceTo,
                        @RequestParam(value = "kmTraveled", required = false) String kmTraveled,
                        @RequestParam(value = "kmPlanned", required = false) String kmPlanned,
                        @RequestParam(value = "collisionDamageWaiver",required = false) boolean cdw,
                        @RequestParam(value = "seatsForKids", required = false) Integer seatsForKids) {


        if(dateFrom.isAfter(dateTo)) {
            return new ResponseEntity<>("Requested date invalid", HttpStatus.BAD_REQUEST);
        }

        if(dateFrom.isBefore(LocalDateTime.now().plusDays(2))) {
            return new ResponseEntity<>("Search should be at least two days in advance", HttpStatus.BAD_REQUEST);
        }

        List<Car> cars = new ArrayList<>();

        List<Advertisement> advertisements = advertisementService.findAdvertisementInRequestedDate(dateFrom, dateTo, pickupSpotId);

        for(Advertisement advertisement : advertisements) {
            Car car = advertisement.getCar();
            if(reservationService.carPossibleToReserveForDate(car, dateFrom, dateTo)) {
                cars.add(advertisement.getCar());
            }
        }

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}
