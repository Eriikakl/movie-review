package hh.sof03.moviereview.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import hh.sof03.moviereview.domain.MovieRepository;


@Controller
public class MovieController {

    @Autowired
    private MovieRepository mrepository;

    // List of movies
    // http://localhost:8080/movielist
    @GetMapping("/movielist")
    public String movieList(Model model) {
        model.addAttribute("movies", mrepository.findAll());
        return "movielist";
    }
    

}
