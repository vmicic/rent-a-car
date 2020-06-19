package com.rentacar.agentbackend.domain.dto;

public class RatingDTO {
	
    private Integer rating;

    private String comment;

    private Long carId;
    
    public RatingDTO() {
    	
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "RatingDTO{" +
                "rating=" + rating +
                ", comment='" + comment + '\'' +
                ", carId=" + carId +
                '}';
    }
}
