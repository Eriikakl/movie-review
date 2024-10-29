package hh.sof03.moviereview.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hh.sof03.moviereview.domain.Movie;
import hh.sof03.moviereview.domain.MovieRepository;
import hh.sof03.moviereview.domain.Review;
import hh.sof03.moviereview.domain.ReviewRepository;
import hh.sof03.moviereview.domain.User;
import hh.sof03.moviereview.domain.UserRepository;

@RestController
@RequestMapping("/api")
public class ReviewRestController {

    @Autowired
    private ReviewRepository rrepository;

    @Autowired
    private UserRepository urepository;

    @Autowired
    private MovieRepository mrepository;

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
    public ResponseEntity<Review> getReviewById(@PathVariable("id") Long reviewid) {
        Optional<Review> review = rrepository.findById(reviewid);
        return review.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // New review
    // http://localhost:8080/api/reviews
    @PostMapping("/reviews")
    public ResponseEntity<Review> newReview(@RequestBody Review review) {

        try {
            if (review.getText() == null || review.getText().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            review.setTime(LocalDateTime.now());
            User user = urepository.findById(review.getUser().getUser_id())
                    .orElseThrow(() -> new RuntimeException("User doesn't exist"));
            review.setUser(user);
            Movie movie = mrepository.findById(review.getMovie().getMovie_id())
                    .orElseThrow(() -> new RuntimeException("Movie doesn't exist"));
            review.setMovie(movie);

            return ResponseEntity.status(HttpStatus.CREATED).body(rrepository.save(review));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Delete review
    // http://localhost:8080/api/reviews/1
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReviewById(@PathVariable("id") Long id) {
        rrepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Edit review
    // http://localhost:8080/api/reviews/1
    @PatchMapping("reviews/{id}")
    public ResponseEntity<Review> editReview(@PathVariable("id") Long id, @RequestBody Review editReview) {
        Optional<Review> review = rrepository.findById(id);

        if (review.isPresent()) {
            Review oldReview = review.get();

            if (editReview.getText() != null) {
                oldReview.setText(editReview.getText());
            }
            if (editReview.getPoints() != null) {
                oldReview.setPoints(editReview.getPoints());
            }
            if (editReview.getTime() != null) {
                oldReview.setTime(LocalDateTime.now());
            }
            rrepository.save(oldReview);
            return ResponseEntity.ok(oldReview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
