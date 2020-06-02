package com.rentacar.advertisementservice.domain.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReservationDTO {
    private LocalDateTime creationDateTime;

    private String status;

    private LocalDateTime dateFrom;

    private LocalDateTime dateTo;

    private ArrayList<Long> reportIds;

    private ArrayList<Long> carIds;

    private Long advertisementId;

    private Long userId;

    private Long companyId;

    public ReservationDTO() {

    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDateTime dateTo) {
        this.dateTo = dateTo;
    }

    public ArrayList<Long> getReportIds() {
        return reportIds;
    }

    public void setReportIds(ArrayList<Long> reportIds) {
        this.reportIds = reportIds;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }


}
