package com.rentacar.userservice.domain;

import javax.persistence.Entity;

import com.sun.istack.NotNull;

@Entity
public class Address extends BaseEntity {
	
	@NotNull
	private String country;
	
	private String state;
	
	@NotNull
	private String city;
	
	private String postalCode;
	
	@NotNull
	private String street;
	
	@NotNull
	private String number;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
