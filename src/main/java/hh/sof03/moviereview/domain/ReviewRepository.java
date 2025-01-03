package hh.sof03.moviereview.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findReviewsByUser(User user);
}
