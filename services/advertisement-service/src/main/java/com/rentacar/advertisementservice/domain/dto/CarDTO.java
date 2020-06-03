package com.rentacar.advertisementservice.domain.dto;

public class CarDTO {

	private Long carModel;
	
	private Long carBrand;
	
	private Long fuelType;
	
	private Long transmissionType;
	
	private Long carClass;
	
	private Double kmTraveled;
	
	private Integer seatsForKids;

	public Long getCarModel() {
		return carModel;
	}

	public void setCarModel(Long carModel) {
		this.carModel = carModel;
	}

	public Long getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(Long carBrand) {
		this.carBrand = carBrand;
	}

	public Long getFuelType() {
		return fuelType;
	}

	public void setFuelType(Long fuelType) {
		this.fuelType = fuelType;
	}

	public Long getTransmissionType() {
		return transmissionType;
	}

	public void setTransmissionType(Long transmissionType) {
		this.transmissionType = transmissionType;
	}

	public Long getCarClass() {
		return carClass;
	}

	public void setCarClass(Long carClass) {
		this.carClass = carClass;
	}

	public Double getKmTraveled() {
		return kmTraveled;
	}

	public void setKmTraveled(Double kmTraveled) {
		this.kmTraveled = kmTraveled;
	}

	public Integer getSeatsForKids() {
		return seatsForKids;
	}

	public void setSeatsForKids(Integer seatsForKids) {
		this.seatsForKids = seatsForKids;
	}
	
	
}
