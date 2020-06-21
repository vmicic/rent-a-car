package com.rentacar.userservice.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Company extends BaseEntity {
	
	private String firstName;
	
	private String lastName;

    private String name;

    private String email;

    private String password;

    private String businessIdNumber;
    
    private CompanyRegistrationState registrationState;
    
    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Advertisement> advertisements = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PriceList> priceLists = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Car> cars = new ArrayList<>();

    public Company() {
    }

    public String getFirstName() {
		return firstName;
	}
    
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getBusinessIdNumber() {
        return businessIdNumber;
    }

    public void setBusinessIdNumber(String businessIdNumber) {
        this.businessIdNumber = businessIdNumber;
    }

	public CompanyRegistrationState getRegistrationState() {
		return registrationState;
	}

	public void setRegistrationState(CompanyRegistrationState registrationState) {
		this.registrationState = registrationState;
	}
}
