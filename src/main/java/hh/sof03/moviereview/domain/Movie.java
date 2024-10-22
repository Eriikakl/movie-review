package hh.sof03.moviereview.domain;

import java.util.List;

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
    private long id;

    private String title;
    private String year;

    @ManyToOne
    @JoinColumn(name="categoryid")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    private List<Review> reviews;

    // Parameterless constructor
    public Movie() {
    }
    
    // Constructor
    public Movie(String title, String year, Category category) {
        this.title = title;
        this.year = year;
        this.category = category;
    }
    
    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
        return "Movie [title=" + title + ", year=" + year + ", category=" + category + "]";
    }
   

    

    
    

}
