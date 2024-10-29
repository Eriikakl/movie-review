package hh.sof03.moviereview.domain;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long category_id;
    
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    @JsonIgnoreProperties("category")
    private List<Movie> movies = new ArrayList<>();

    // Parameterless constructor
    public Category() {
    }

    // Constructor
    public Category(String name) {
        this.name = name;
    }

     // Getters and Setters
     
    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long id) {
        this.category_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    // ToString without id and movies list
    @Override
    public String toString() {
        return "Category [name=" + name + "]";
    }

    
}
