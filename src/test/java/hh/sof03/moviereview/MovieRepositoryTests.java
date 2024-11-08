package hh.sof03.moviereview;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.moviereview.domain.Category;
import hh.sof03.moviereview.domain.CategoryRepository;
import hh.sof03.moviereview.domain.Movie;
import hh.sof03.moviereview.domain.MovieRepository;
import hh.sof03.moviereview.domain.Review;
import hh.sof03.moviereview.domain.ReviewRepository;
import hh.sof03.moviereview.domain.User;
import hh.sof03.moviereview.domain.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository mrepository;
    @Autowired
    private CategoryRepository crepository;
    @Autowired
    private ReviewRepository rrepository;
    @Autowired
    private UserRepository urepository;

    // Testataan elokuvan etsiminen id:llä
    @Test
    public void findByMovieId() {
        Category category = new Category("horror");
        crepository.save(category);
        Movie movie = new Movie("Elokuvaesimerkki", "2024", category);
        mrepository.save(movie);
        Long movieid = movie.getMovie_id();
        Optional<Movie> foundedMovie = mrepository.findById(movieid);

        assertThat(foundedMovie).isPresent();
        assertThat(foundedMovie.get().getTitle()).isEqualTo("Elokuvaesimerkki");
    }

    // Testataan uuden elokuvan lisäys
    @Test
    public void createNewMovie() {
        Category category = new Category("horror");
        crepository.save(category);
        Movie movie = new Movie("Elokuvaesimerkki", "2024", category);
        mrepository.save(movie);
        assertThat(movie.getMovie_id()).isNotNull();
    }

    // Testataan löytää username:n perusteella
    @Test
    public void findByUsernameReturnUser() {
        User user = new User("username", "username@email.com", "salasana", "user");
        urepository.save(user);
        User foundedUser = urepository.findByUsername("username");
        assertThat(foundedUser).isNotNull();
        assertThat(foundedUser.getEmail()).isEqualTo("username@email.com");
    }

    // Testataan uuden arvostelun lisäys
    @Test
     public void createNewReview() {
         Category category = new Category("horror");
         crepository.save(category);
         Movie movie = new Movie("Elokuvaesimerkki", "2024", category);
         mrepository.save(movie);
         User user = new User("username", "username@email.com", "salasana", "user");
        urepository.save(user);
         LocalDateTime timestamp = LocalDateTime.now();
        Review review = new Review("Ihan ok", 3, timestamp, user, movie);

         assertThat(review.getReview_id()).isNotNull();
     }

     // Testataan arvostelun poisto
    @Test
    public void deleteReview() {
        Category category = new Category("horror");
        crepository.save(category);
        Movie movie = new Movie("Elokuvaesimerkki", "2024", category);
        mrepository.save(movie);
        User user = new User("username", "username@email.com", "salasana", "user");
       urepository.save(user);
        LocalDateTime timestamp = LocalDateTime.now();
       Review review = new Review("Ihan ok", 3, timestamp, user, movie);
       rrepository.save(review);

        Long reviewid = review.getReview_id();
        rrepository.deleteById(reviewid);
        Optional<Review> deletedReview = rrepository.findById(reviewid);

        assertThat(deletedReview).isNotPresent();
    }

}
