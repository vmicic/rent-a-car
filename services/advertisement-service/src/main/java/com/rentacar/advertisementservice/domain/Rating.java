package com.rentacar.advertisementservice.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Rating extends BaseEntity {

    private Integer rating;

    private String reply;

    private String comment;

    private boolean approved;

    private boolean seen;


    @OneToOne
    @JoinColumn(name = "user_posted_id", referencedColumnName = "id")
    private User ratingPosted;

    @OneToOne
    @JoinColumn(name = "user_received_id", referencedColumnName = "id")
    private User ratingReceived;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    public Rating() {
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public User getRatingPosted() {
        return ratingPosted;
    }

    public void setRatingPosted(User ratingPosted) {
        this.ratingPosted = ratingPosted;
    }

    public User getRatingReceived() {
        return ratingReceived;
    }

    public void setRatingReceived(User ratingReceived) {
        this.ratingReceived = ratingReceived;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }


    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
