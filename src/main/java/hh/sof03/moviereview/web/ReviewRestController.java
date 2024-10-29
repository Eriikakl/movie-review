package hh.sof03.moviereview.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import hh.sof03.moviereview.domain.Review;
import hh.sof03.moviereview.domain.ReviewRepository;


@RestController
@RequestMapping("/api")
public class ReviewRestController {


        @Autowired
    private ReviewRepository rrepository;

      // List of Reviews
      // http://localhost:8080/api/reviews
    @GetMapping("/reviews")
    public List<Review> getAllReviews() {
        List<Review> reviews = (List<Review>) rrepository.findAll();
        return reviews;
    }

     // Get review by id
    // http://localhost:8080/api/reviews/1
    @GetMapping("/reviews/{id}")
    public @ResponseBody Optional<Review> getReviewById(@PathVariable("id") Long reviewid) {
       
        return rrepository.findById(reviewid);
    }

    // New review 
    // http://localhost:8080/api/reviews
    @PostMapping("/reviews")
    public @ResponseBody Review newReview(@RequestBody Review review) {
        review.setTime(LocalDateTime.now());
        rrepository.save(review);
        return rrepository.save(review);
    }

}
