package com.rentacar.searchservice.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company extends BaseEntity {

    private String name;

    private String email;

    private String password;

    private String address;

    private String businessIdNumber;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessIdNumber() {
        return businessIdNumber;
    }

    public void setBusinessIdNumber(String businessIdNumber) {
        this.businessIdNumber = businessIdNumber;
    }
}
