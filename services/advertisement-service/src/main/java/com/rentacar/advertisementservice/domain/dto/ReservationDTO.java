package com.rentacar.advertisementservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationDTO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fromDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime toDate;

    private List<Long> carIds = new ArrayList<>();

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

    public List<Long> getCarIds() {
        return carIds;
    }

    public void setCarIds(List<Long> carIds) {
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
