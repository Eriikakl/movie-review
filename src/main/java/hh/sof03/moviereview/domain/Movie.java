package hh.sof03.moviereview.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long movie_id;

    private String title;
    private String release_year;

    @ManyToOne
    @JsonIgnoreProperties("movies")
    @JoinColumn(name="category_id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    @JsonIgnoreProperties("movie")
    private List<Review> reviews;

    // Parameterless constructor
    public Movie() {
    }
    
    // Constructor
    public Movie(String title, String year, Category category) {
        this.title = title;
        this.release_year = year;
        this.category = category;
    }
    
    // Getters and Setters

    public long getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(long id) {
        this.movie_id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_year() {
        return release_year;
    }

    public void setRelease_year(String year) {
        this.release_year = year;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    // ToString without id and reviews list
    @Override
    public String toString() {
        return "Movie [title=" + title + ", year=" + release_year + ", category=" + category + "]";
    }
   

    

    
    

}
