package hh.sof03.moviereview;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.moviereview.web.CategoryRestController;
import hh.sof03.moviereview.web.MovieController;
import hh.sof03.moviereview.web.MovieRestController;
import hh.sof03.moviereview.web.ReviewController;
import hh.sof03.moviereview.web.ReviewRestController;
import hh.sof03.moviereview.web.UserController;
import hh.sof03.moviereview.web.UserRestController;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MoviereviewApplicationTests {

	@Autowired
	private MovieController moviecontroller;

	@Autowired
	private ReviewController reviewcontroller;

	@Autowired
	private UserController usercontroller;

	@Autowired
	private MovieRestController movierestcontroller;
	
	@Autowired
	private CategoryRestController categoryrestcontroller;

	@Autowired
	private ReviewRestController reviewrestcontroller;

	@Autowired
	private UserRestController userrestcontroller;


	@Test
	void contextLoads() {
		assertThat(moviecontroller).isNotNull();
		assertThat(reviewcontroller).isNotNull();
		assertThat(usercontroller).isNotNull();
		assertThat(categoryrestcontroller).isNotNull();
		assertThat(movierestcontroller).isNotNull();
		assertThat(reviewrestcontroller).isNotNull();
		assertThat(userrestcontroller).isNotNull();
	}

}
