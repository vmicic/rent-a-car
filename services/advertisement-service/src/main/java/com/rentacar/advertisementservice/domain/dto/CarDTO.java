package com.rentacar.advertisementservice.domain.dto;

import java.awt.Image;
import java.util.Set;

public class CarDTO {

	private Long carModelId;
	
	private Long carBrandId;
	
	private Long fuelTypeId;
	
	private Long transmissionTypeId;
	
	private Long carClassId;
	
	private Double kmTraveled;
	
	private Integer seatsForKids;
	
	private byte[] image;

	public Long getCarModelId() {
		return carModelId;
	}

	public void setCarModelId(Long carModelId) {
		this.carModelId = carModelId;
	}

	public Long getCarBrandId() {
		return carBrandId;
	}

	public void setCarBrandId(Long carBrandId) {
		this.carBrandId = carBrandId;
	}

	public Long getFuelTypeId() {
		return fuelTypeId;
	}

	public void setFuelTypeId(Long fuelTypeId) {
		this.fuelTypeId = fuelTypeId;
	}

	public Long getTransmissionTypeId() {
		return transmissionTypeId;
	}

	public void setTransmissionTypeId(Long transmissionTypeId) {
		this.transmissionTypeId = transmissionTypeId;
	}

	public Long getCarClassId() {
		return carClassId;
	}

	public void setCarClassId(Long carClassId) {
		this.carClassId = carClassId;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
		
}
