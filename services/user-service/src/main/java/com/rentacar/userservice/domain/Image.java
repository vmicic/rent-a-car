package com.rentacar.userservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Image extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Lob
	@Column(name = "data")
	private byte[] data;

	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    protected Car car;
	
	public Image() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}
