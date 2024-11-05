package hh.sof03.moviereview.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.sof03.moviereview.domain.Movie;
import hh.sof03.moviereview.domain.MovieRepository;
import hh.sof03.moviereview.domain.Review;
import hh.sof03.moviereview.domain.ReviewRepository;
import hh.sof03.moviereview.domain.User;
import hh.sof03.moviereview.domain.UserRepository;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository rrepository;

    @Autowired
    private MovieRepository mrepository;

    @Autowired
    private UserRepository urepository;

    // List of reviews
    // http://localhost:8080/reviewlist
    @RequestMapping(value = "/reviewlist", method = RequestMethod.GET)
    public String reviewList(Model model) {
        model.addAttribute("reviews", rrepository.findAll());
        return "reviewlist";
    }

    // Add review
    // http://localhost:8080/addreview
    @RequestMapping(value = "/addreview")
    public String addReview(Model model) {

        model.addAttribute("review", new Review());

        List<Movie> movies = new ArrayList<>();
        for (Movie movie : mrepository.findAll()) {
            movies.add(movie);
        }
        model.addAttribute("movies", movies);
        return "addreview";
    }

    // Save new review
    @RequestMapping(value = "/savereview", method = RequestMethod.POST)
    public String saveReview(Review review, Authentication authentication) {
        review.setTime(LocalDateTime.now());
        String username = authentication.getName();
        User user = urepository.findByUsername(username);
        review.setUser(user);
        rrepository.save(review);
        return "redirect:reviewlist";
    }

    // Delete review
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteReview(@PathVariable("id") Long id, Model model, Authentication authentication) {
        String username = authentication.getName();
        Review review = rrepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Arviota ei löytynyt"));

        // Tarkistetaan käyttäjä
        if (review.getUser().getUsername().equals(username) || authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN"))) {
            rrepository.delete(review);

        } else {
            throw new IllegalArgumentException("Et voi poistaa tätä arvioita, koska et ole sen tekijä");
        }

        return "redirect:../reviewlist";
    }
}
