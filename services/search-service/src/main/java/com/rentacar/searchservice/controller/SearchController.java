package com.rentacar.searchservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @GetMapping("/search")
    public void findCars(@RequestParam("dateFrom") String dateFrom,
                        @RequestParam("dateTo") String dateTo,
                        @RequestParam("carBrand") String carBrand,
                        @RequestParam("carModel") String carModel,
                        @RequestParam("fuelType") String fuelType,
                        @RequestParam("carClass") String carClass,
                        @RequestParam("priceFrom") String priceFrom,
                        @RequestParam("priceTo") String priceTo,
                        @RequestParam("kmTraveled") String kmTraveled,
                        @RequestParam("kmPlanned") String kmPlanned,
                        @RequestParam("collisionDamageWaiver") boolean cdw,
                        @RequestParam("seatsForKids") Integer seatsForKids) {

    }
}
