package com.rentacar.administratorservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Advertisement extends BaseEntity{

    private LocalDateTime fromDate;

    private LocalDateTime toDate;

    private Double kmLimitPerDay;

    @JsonIgnore
    @OneToMany(mappedBy = "advertisement")
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "advertisement_pickup_spots",
            joinColumns = @JoinColumn(name = "advertisement_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pickup_spot_id", referencedColumnName = "id"))
    private List<PickupSpot> pickupSpots;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "price_list_id")
    private PriceList priceList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    public Advertisement() {
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

    public Double getKmLimitPerDay() {
        return kmLimitPerDay;
    }

    public void setKmLimitPerDay(Double kmLimitPerDay) {
        this.kmLimitPerDay = kmLimitPerDay;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<PickupSpot> getPickupSpots() {
        return pickupSpots;
    }

    public void setPickupSpots(List<PickupSpot> pickupSpots) {
        this.pickupSpots = pickupSpots;
    }
}
