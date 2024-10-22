package hh.sof03.moviereview.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.sof03.moviereview.domain.Movie;
import hh.sof03.moviereview.domain.MovieRepository;
import hh.sof03.moviereview.domain.Review;
import hh.sof03.moviereview.domain.ReviewRepository;

@Controller
public class ReviewController {


    @Autowired
    private ReviewRepository rrepository;

    @Autowired
    private MovieRepository mrepository;
    
    // List of reviews
    // http://localhost:8080/reviewlist
    @GetMapping("/reviewlist")
    public String reviewList(Model model) {
        model.addAttribute("reviews", rrepository.findAll());
        return "reviewlist";
    }
    
     // Add review
     // http://localhost:8080/addreview
    @RequestMapping(value="/addreview")
    public String addReview(Model model) {

        model.addAttribute("review", new Review());

        List<Movie> movies = new ArrayList<>();
        for (Movie movie : mrepository.findAll()) {
            movies.add(movie);
        }
        model.addAttribute("movies", movies);
        return "addreview";
    }

    // User details later!!
     // Save new review 
    @RequestMapping(value="/savereview", method = RequestMethod.POST)
    public String saveReview(Review review) {
        review.setTime(LocalDateTime.now());
        rrepository.save(review);
        return "redirect:reviewlist";
    }

     // Delete review
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteReview(@PathVariable("id") Long id, Model model) {
        rrepository.deleteById(id);
        return "redirect:../reviewlist";
    }
}
