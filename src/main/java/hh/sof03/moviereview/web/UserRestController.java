package hh.sof03.moviereview.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hh.sof03.moviereview.domain.User;
import hh.sof03.moviereview.domain.UserRepository;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserRepository urepository;

    // List of Users
    // http://localhost:8080/api/users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users = (List<User>) urepository.findAll();
        return users;
    }

    // Get user by id
    // http://localhost:8080/api/users/1
    @GetMapping("/users/{id}")
    public @ResponseBody Optional<User> getUserById(@PathVariable("id") Long userid) {

        return urepository.findById(userid);
    }

    // New user
    // http://localhost:8080/api/users
    @PostMapping("/users")
    public @ResponseBody User newUser(@RequestBody User user) {
        return urepository.save(user);
    }

    // Delete user
    // http://localhost:8080/api/users/1
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
        urepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
