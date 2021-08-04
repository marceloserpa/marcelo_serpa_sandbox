package com.marceloserpa.sparkes;

import java.io.Serializable;
import java.util.Date;

public class Rating implements Serializable {

    private Integer userId;
    private Integer movieId;
    private Double rating;
    private Date date;

    public Rating(Integer userId, Integer movieId, Double rating, Date date) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.date = date;
    }

    public Rating() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
