package hh.sof03.moviereview.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import hh.sof03.moviereview.domain.Category;
import hh.sof03.moviereview.domain.CategoryRepository;
import hh.sof03.moviereview.domain.Movie;
import hh.sof03.moviereview.domain.MovieRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class MovieRestController {

    @Autowired
    private MovieRepository mrepository;

    @Autowired
    private CategoryRepository crepository;

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
    public ResponseEntity<Movie> getBookById(@PathVariable("id") Long movieid) {
        Optional<Movie> movie = mrepository.findById(movieid);
        return movie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // New movie
    // http://localhost:8080/api/movies
    @PostMapping("/movies")
    public ResponseEntity<Movie> newMovie(@RequestBody Movie movie) {
        try {
            if (movie.getTitle() == null || movie.getTitle().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            Category category = crepository.findById(movie.getCategory().getCategory_id())
                    .orElseThrow(() -> new RuntimeException("Category doesn't exist"));
            movie.setCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(mrepository.save(movie));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Delete movie
    // http://localhost:8080/api/movies/1
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable("id") Long id) {
        mrepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
