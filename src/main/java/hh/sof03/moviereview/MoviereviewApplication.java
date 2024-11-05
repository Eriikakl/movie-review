package hh.sof03.moviereview;


import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.moviereview.domain.Category;
import hh.sof03.moviereview.domain.CategoryRepository;
import hh.sof03.moviereview.domain.Movie;
import hh.sof03.moviereview.domain.MovieRepository;
import hh.sof03.moviereview.domain.Review;
import hh.sof03.moviereview.domain.ReviewRepository;
import hh.sof03.moviereview.domain.User;
import hh.sof03.moviereview.domain.UserRepository;

@SpringBootApplication
public class MoviereviewApplication {

	private static final Logger log = LoggerFactory.getLogger(MoviereviewApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MoviereviewApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CategoryRepository crepository, MovieRepository mrepository, ReviewRepository rrepository, UserRepository urepository) {
		return (args) -> {

			LocalDateTime time = LocalDateTime.now();
			Category category1 = new Category("comedy");
			crepository.save(category1);
			Movie movie1 = new Movie("Moulin Rouge!", "2001", category1);
			mrepository.save(movie1);
			User user1 = new User("admin123", "admin@email.com", "$2a$10$f7Etycg4IlnKvVzQGDZg4.YbGU5DQLo.m6muBzzdmO4yF.OJEDKJe", "ADMIN");
			urepository.save(user1);
			User user2 = new User("user123", "user@email.com", "$2a$10$DSmRsvZjOlGLSrE5tslcM.pm.JEo9uFXfPQSVi6GUVVd0j0tWmW3K", "USER");
			urepository.save(user2);
			Review review1 = new Review("hyvä elokuva!!", 5, time, user1, movie1);
			rrepository.save(review1); 

			log.info("fetch all the categories");
			for (Category category : crepository.findAll()) {
				log.info(category.toString());
			}
			log.info("fetch all the movies");
			for (Movie movie : mrepository.findAll()) {
				log.info(movie.toString());
			}
			log.info("fetch all the users");
			for (User user: urepository.findAll()) {
				log.info(user.toString());
			}
			log.info("fetch all the reviews");
			for (Review review: rrepository.findAll()) {
				log.info(review.toString());
			}
			
			

		};
	}

}
