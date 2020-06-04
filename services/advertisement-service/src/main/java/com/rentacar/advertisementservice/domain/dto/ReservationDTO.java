package com.rentacar.advertisementservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReservationDTO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fromDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime toDate;

    private ArrayList<Long> carIds;

    private Long advertisementId;

    public ReservationDTO() {

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

    public ArrayList<Long> getCarIds() {
        return carIds;
    }

    public void setCarIds(ArrayList<Long> carIds) {
        this.carIds = carIds;
    }

    public Long getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(Long advertisementId) {
        this.advertisementId = advertisementId;
    }


    @Override
    public String toString() {
        return "ReservationDTO{" +
                "dateFrom=" + fromDate +
                ", dateTo=" + toDate +
                ", carIds=" + carIds +
                ", advertisementId=" + advertisementId +
                '}';
    }
}
