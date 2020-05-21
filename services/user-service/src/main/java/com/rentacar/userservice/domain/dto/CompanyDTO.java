package com.rentacar.userservice.domain.dto;

public class CompanyDTO {

    private String name;

    private String email;

    private String password;

    private String address;

    private String businessIdNumber;

    public CompanyDTO() {
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
