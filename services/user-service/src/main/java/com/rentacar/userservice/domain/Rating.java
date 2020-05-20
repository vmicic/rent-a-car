package com.rentacar.userservice.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Rating extends BaseEntity {

    private Integer rating;

    private String reply;

    @OneToOne
    @JoinColumn(name = "user_posted_id", referencedColumnName = "id")
    private User ratingPosted;

    @OneToOne
    @JoinColumn(name = "user_received_id", referencedColumnName = "id")
    private User ratingReceived;

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
}
