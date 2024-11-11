package hh.sof03.moviereview.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.sof03.moviereview.domain.CategoryRepository;
import hh.sof03.moviereview.domain.Movie;
import hh.sof03.moviereview.domain.MovieRepository;

@Controller
public class MovieController {

    @Autowired
    private MovieRepository mrepository;

    @Autowired
    private CategoryRepository crepository;

    // List of movies
    // http://localhost:8080/movielist
    @GetMapping("/movielist")
    public String movieList(Model model) {
        model.addAttribute("movies", mrepository.findAll());
        return "movielist";
    }

    // Add new movie
    // http://localhost:8080/addmovie
    @GetMapping("/addmovie")
    public String addMovie(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("categories", crepository.findAll());
        return "addmovie";
    }

    // Update movie
    // http://localhost:8080/editmovie/1
    @GetMapping("/editmovie/{id}")
    public String editMovie(@PathVariable("id") Long movieid, Model model) {
        model.addAttribute("movie", mrepository.findById(movieid));
        model.addAttribute("categories", crepository.findAll());

        return "editmovie";
    }

    // Save added movie
    // http://localhost:8080/saveadded
    @PostMapping("/saveadded")
    public String saveAddedMovie(Movie movie) {
        mrepository.save(movie);
        return "redirect:movielist";
    }

    // Save updated movie
    // http://localhost:8080/saveupdated
    @PostMapping("/saveupdated")
    public String saveUpdatedMovie(Movie movie) {
        mrepository.save(movie);
        return "redirect:movielist";
    }

    // Delete movie
    // http://localhost:8080/deletemovie/1
    @GetMapping("/deletemovie/{id}")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteMovie(@PathVariable("id") Long movieid, Model model) {
        mrepository.deleteById(movieid);
        return "redirect:../movielist";
    }
}
