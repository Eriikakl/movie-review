package hh.sof03.moviereview.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import hh.sof03.moviereview.domain.Category;
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
    @PostMapping("/addmovie")
     public String addMovie(Model model) {
        model.addAttribute("movie", new Movie());

        List<Category> categories = new ArrayList<>();
        for (Category category : crepository.findAll()) {
            categories.add(category);
        }
        model.addAttribute("categories", categories);
        return "addmovie";
     }

    // Update book
     // http://localhost:8080/edit/1
    @PatchMapping("/edit/{id}")
    public String editMovie(@PathVariable("id") Long movieid, Model model) {
        Movie movie = mrepository.findById(movieid).orElse(null);
        model.addAttribute("movie", movie);

        List<Category> categories = new ArrayList<>();
        for (Category category : crepository.findAll()) {
            categories.add(category);
        }
        model.addAttribute("categories", categories);

        return "editmovie";
    }

    // Save added movie
     // http://localhost:8080/saveadded
    @GetMapping("/saveadded")
    public String saveAddedMovie(Movie movie) {
        mrepository.save(movie);
        return "redirect:movielist";
    }

    // Save updated movie
     // http://localhost:8080/saveupdated
    @GetMapping("/saveupdated")
    public String saveUpdatedMovie(Movie movie) {
        mrepository.save(movie);
        return "redirect:movielist";
    }

    // Delete movie
     // http://localhost:8080/delete
    @GetMapping("/delete")
    public String deleteMovie(@PathVariable("id") Long movieid, Model model) {
        mrepository.deleteById(movieid);
        return "redirect:../movielist";
    }
}
