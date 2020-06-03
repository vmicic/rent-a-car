package com.rentacar.advertisementservice.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Discount extends BaseEntity {

    private Integer daysRequired;

    private Double discountPercentage;

    @OneToMany(mappedBy = "discount")
    private List<Advertisement> advertisement;

    public Discount() {
    }

    public Integer getDaysRequired() {
        return daysRequired;
    }

    public void setDaysRequired(Integer daysRequired) {
        this.daysRequired = daysRequired;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public List<Advertisement> getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(List<Advertisement> advertisement) {
        this.advertisement = advertisement;
    }
}
