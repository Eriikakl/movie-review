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
import hh.sof03.moviereview.domain.Users;
import hh.sof03.moviereview.domain.UsersRepository;

@SpringBootApplication
public class MoviereviewApplication {

	private static final Logger log = LoggerFactory.getLogger(MoviereviewApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MoviereviewApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CategoryRepository crepository, MovieRepository mrepository, ReviewRepository rrepository, UsersRepository urepository) {
		return (args) -> {

			LocalDateTime time = LocalDateTime.now();
			Category category1 = new Category("comedy");
			crepository.save(category1);
			Movie movie1 = new Movie("Moulin Rouge!", "2001", category1);
			mrepository.save(movie1);
			Users user1 = new Users("tunnus123", "email@email.com", "salanasana");
			urepository.save(user1);
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
			for (Users user: urepository.findAll()) {
				log.info(user.toString());
			}
			log.info("fetch all the reviews");
			for (Review review: rrepository.findAll()) {
				log.info(review.toString());
			}
			
			

		};
	}

}
