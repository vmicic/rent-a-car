package com.rentacar.advertisementservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Car extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "car_model_id", referencedColumnName = "id")
    private CarModel carModel;

    @ManyToOne
    @JoinColumn(name = "car_brand_id", referencedColumnName = "id")
    private CarBrand carBrand;

    @ManyToOne
    @JoinColumn(name = "fuel_type_id", referencedColumnName = "id")
    private FuelType fuelType;

    @ManyToOne
    @JoinColumn(name = "transmission_type_id", referencedColumnName = "id")
    private TransmissionType transmissionType;

    @ManyToOne
    @JoinColumn(name = "car_class_id", referencedColumnName = "id")
    private CarClass carClass;

    private Double kmTraveled;

    private Integer seatsForKids;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @JsonIgnore
    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    private List<Image> image;

    @OneToMany(mappedBy = "car")
    private List<Rating> ratings;

	public Car() {
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }

    public Double getKmTraveled() {
        return kmTraveled;
    }

    public void setKmTraveled(Double kmTraveled) {
        this.kmTraveled = kmTraveled;
    }

    public Integer getSeatsForKids() {
        return seatsForKids;
    }

    public void setSeatsForKids(Integer seatsForKids) {
        this.seatsForKids = seatsForKids;
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

	public List<Image> getImage() {
		return image;
	}

	public void setImage(List<Image> image) {
		this.image = image;
	}

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return  Objects.equals(this.getId(), car.getId()) &&
                Objects.equals(carModel, car.carModel) &&
                Objects.equals(carBrand, car.carBrand) &&
                Objects.equals(fuelType, car.fuelType) &&
                Objects.equals(transmissionType, car.transmissionType) &&
                Objects.equals(carClass, car.carClass) &&
                Objects.equals(kmTraveled, car.kmTraveled) &&
                Objects.equals(seatsForKids, car.seatsForKids) &&
                Objects.equals(user, car.user) &&
                Objects.equals(company, car.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), carModel, carBrand, fuelType, transmissionType, carClass, kmTraveled, seatsForKids, user, company);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carModel=" + carModel +
                ", carBrand=" + carBrand +
                ", fuelType=" + fuelType +
                ", transmissionType=" + transmissionType +
                ", carClass=" + carClass +
                '}';
    }
}
