package com.rentacar.messageservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "reservation")
public class Reservation extends BaseEntity {


    @Enumerated(EnumType.STRING)
    private ReservationState state;

    private LocalDateTime fromDate;

    private LocalDateTime toDate;


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
