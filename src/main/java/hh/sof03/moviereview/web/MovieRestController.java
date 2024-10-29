package hh.sof03.moviereview.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hh.sof03.moviereview.domain.Movie;
import hh.sof03.moviereview.domain.MovieRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class MovieRestController {

    @Autowired
    private MovieRepository mrepository;

    // List of Movies
    // http://localhost:8080/api/movies
    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        List<Movie> movies = (List<Movie>) mrepository.findAll();
        return movies;
    }

    // Get movie by id
    // http://localhost:8080/api/movies/1
    @GetMapping("/movies/{id}")
    public @ResponseBody Optional<Movie> getBookById(@PathVariable("id") Long movieid) {

        return mrepository.findById(movieid);
    }

    // New movie
    // http://localhost:8080/api/movies
    @PostMapping("/movies")
    public @ResponseBody Movie newMovie(@RequestBody Movie movie) {
        return mrepository.save(movie);
    }

    // Delete movie
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable("id") Long id) {
        mrepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
