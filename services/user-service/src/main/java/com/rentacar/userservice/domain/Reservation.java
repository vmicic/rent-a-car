package com.rentacar.userservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reservation extends BaseEntity {

    private LocalDateTime creationDateTime;

    @Enumerated(EnumType.STRING)
    private ReservationState state;

    private LocalDateTime fromDate;

    private LocalDateTime toDate;

    private Double price;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    private List<Report> reports = new ArrayList<>();


    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "car_reservation",
                joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"))
    private List<Car> cars = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "advertisement_id")
    private Advertisement advertisement;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_requested_id", referencedColumnName = "id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_received_id", referencedColumnName = "id")
    private User userOwnerCar;

    public Reservation() {
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public ReservationState getState() {
        return state;
    }

    public void setState(ReservationState state) {
        this.state = state;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public User getUserOwnerCar() {
        return userOwnerCar;
    }

    public void setUserOwnerCar(User userOwnerCar) {
        this.userOwnerCar = userOwnerCar;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
