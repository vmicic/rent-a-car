package com.rentacar.advertisementservice.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    private String email;

    private String firstName;

    private String lastName;

    private LocalDateTime dateRegistered;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservationsRequested = new ArrayList<>();

    @OneToMany(mappedBy = "userOwnerCar")
    private List<Reservation> reservationReceived = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Advertisement> advertisements = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PriceList> priceLists = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Car> cars = new ArrayList<>();

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(LocalDateTime dateRegistered) {
        this.dateRegistered = dateRegistered;
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

    public List<Reservation> getReservationsRequested() {
        return reservationsRequested;
    }

    public void setReservationsRequested(List<Reservation> reservationsRequested) {
        this.reservationsRequested = reservationsRequested;
    }

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }

    public List<PriceList> getPriceLists() {
        return priceLists;
    }

    public void setPriceLists(List<PriceList> priceLists) {
        this.priceLists = priceLists;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Reservation> getReservationReceived() {
        return reservationReceived;
    }

    public void setReservationReceived(List<Reservation> reservationReceived) {
        this.reservationReceived = reservationReceived;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(this.getId(), user.getId()) &&
                Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), email, firstName, lastName);
    }
}
