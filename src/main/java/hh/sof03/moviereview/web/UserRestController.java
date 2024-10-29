package hh.sof03.moviereview.web;

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
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userid) {
        Optional<User> user = urepository.findById(userid);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // New user
    // http://localhost:8080/api/users
    @PostMapping("/users")
    public ResponseEntity<User> newUser(@RequestBody User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(urepository.save(user));
    }

    // Delete user
    // http://localhost:8080/api/users/1
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
        urepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Edit User
    // http://localhost:8080/api/users/1
    @PatchMapping("users/{id}")
    public ResponseEntity<User> editUser(@PathVariable("id") Long id, @RequestBody User editUser) {
        Optional<User> user = urepository.findById(id);

        if (user.isPresent()) {
            User oldUser = user.get();

            if (editUser.getUsername() != null) {
                oldUser.setUsername(editUser.getUsername());
            }
            if (editUser.getEmail() != null) {
                oldUser.setEmail(editUser.getEmail());
            }
            if (editUser.getPassword() != null) {
                oldUser.setPassword(editUser.getPassword());
            }
            if (editUser.getRole() != null) {
                oldUser.setRole(editUser.getRole());
            }
            urepository.save(oldUser);
            return ResponseEntity.ok(oldUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
