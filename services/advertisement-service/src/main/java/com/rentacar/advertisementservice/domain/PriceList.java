package com.rentacar.advertisementservice.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PriceList extends BaseEntity {

    private Double pricePerDay;

    private Double priceAboveKmLimit;

    private Double priceCollisionDamageWaiver;

    @OneToMany(mappedBy = "discount")
    private List<Advertisement> advertisement = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public PriceList() {
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Double getPriceAboveKmLimit() {
        return priceAboveKmLimit;
    }

    public void setPriceAboveKmLimit(Double priceAboveKmLimit) {
        this.priceAboveKmLimit = priceAboveKmLimit;
    }

    public Double getPriceCollisionDamageWaiver() {
        return priceCollisionDamageWaiver;
    }

    public void setPriceCollisionDamageWaiver(Double priceCollisionDamageWaiver) {
        this.priceCollisionDamageWaiver = priceCollisionDamageWaiver;
    }

    public List<Advertisement> getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(List<Advertisement> advertisement) {
        this.advertisement = advertisement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
