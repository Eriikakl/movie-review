package hh.sof03.moviereview.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long review_id;

    private String text;
    private Integer points;
    private LocalDateTime time;
    
    @ManyToOne
    //@JsonIgnoreProperties("reviews")
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    //@JsonIgnoreProperties("reviews")
    @JoinColumn(name="movie_id")
    private Movie movie;

    // Parameterless constructor
    public Review() {
    }

    // Constructor
    public Review(String text, Integer points, LocalDateTime time, User user, Movie movie) {
        this.text = text;
        this.points = points;
        this.time = time;
        this.user = user;
        this.movie = movie;
    }

     // Getters and Setters

    public long getReview_id() {
        return review_id;
    }

    public void setReview_id(long id) {
        this.review_id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    // ToString without id
    @Override
    public String toString() {
        return "Review [text=" + text + ", points=" + points + ", time=" + time + ", user=" + user + ", movie=" + movie
                + "]";
    }

    

}
