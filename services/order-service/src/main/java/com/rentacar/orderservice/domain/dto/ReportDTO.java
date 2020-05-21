package com.rentacar.orderservice.domain.dto;

public class ReportDTO {

	private Double kmTraveled;

    private String comment;
    
    private Long reservationId;
    
    public ReportDTO() {
    	
    }

	public Double getKmTraveled() {
		return kmTraveled;
	}

	public void setKmTraveled(Double kmTraveled) {
		this.kmTraveled = kmTraveled;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
    
    
}
