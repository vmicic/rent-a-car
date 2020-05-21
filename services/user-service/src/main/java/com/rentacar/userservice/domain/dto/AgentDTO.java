package com.rentacar.userservice.domain.dto;

public class AgentDTO {

    private String firstName;

    private String email;

    private String lastName;

    private String password;

    private String address;

    private String businessIdNumber;

    public AgentDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
