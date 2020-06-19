package com.rentacar.advertisementservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdvertisementDTO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fromDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime toDate;

    private List<Long> pickupSpots = new ArrayList<>();

    private Long carId;

    public AdvertisementDTO() {
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public List<Long> getPickupSpots() {
        return pickupSpots;
    }

    public void setPickupSpots(List<Long> pickupSpots) {
        this.pickupSpots = pickupSpots;
    }
}
