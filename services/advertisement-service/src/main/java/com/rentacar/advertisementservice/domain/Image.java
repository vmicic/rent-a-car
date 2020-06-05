package com.rentacar.advertisementservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

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
