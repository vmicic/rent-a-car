package com.rentacar.agentbackend.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Report extends BaseEntity {

    private Double kmTraveled;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private Reservation reservation;

    public Report() {
    }

    public Double getKmTraveled() {
        return kmTraveled;
    }

    public void setKmTraveled(Double kmTraveled) {
        this.kmTraveled = kmTraveled;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
