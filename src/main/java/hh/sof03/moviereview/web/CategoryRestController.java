package hh.sof03.moviereview.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hh.sof03.moviereview.domain.Category;
import hh.sof03.moviereview.domain.CategoryRepository;

@RestController
@RequestMapping("/api")
public class CategoryRestController {

    @Autowired
    private CategoryRepository crepository;

    // List of Category
    // http://localhost:8080/api/categories
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        List<Category> categories = (List<Category>) crepository.findAll();
        return categories;
    }

    // Get category by id
    // http://localhost:8080/api/categories/1
    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long categoryid) {
        Optional<Category> category = crepository.findById(categoryid);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // New category
    // http://localhost:8080/api/categories
    @PostMapping("/categories")
    public ResponseEntity<Category> newCategory(@RequestBody Category category) {
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(crepository.save(category));
    }

    // Delete category
    // http://localhost:8080/api/categories/1
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable("id") Long id) {
        crepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
