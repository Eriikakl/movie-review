package hh.sof03.moviereview.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hh.sof03.moviereview.domain.Review;
import hh.sof03.moviereview.domain.ReviewRepository;
import hh.sof03.moviereview.domain.User;
import hh.sof03.moviereview.domain.UserRepository;


@Controller
public class UserController {

      @Autowired
    private ReviewRepository rrepository;

    @Autowired
    private UserRepository urepository;

    @GetMapping("/userprofile")
    public String userProfile(Authentication authentication, Model model) {
        String username = authentication.getName();
        model.addAttribute("username", username);
        User user = urepository.findByUsername(username);
        List<Review> userReviews = rrepository.findReviewsByUser(user);
        model.addAttribute("reviews", userReviews);
        return "userprofile";
    }

   
    
}
