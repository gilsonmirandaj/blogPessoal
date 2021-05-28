package blog.src.main.java.PersonalBlog.controller;

import blog.src.main.java.PersonalBlog.model.Theme;
import blog.src.main.java.PersonalBlog.repository.RepositoryTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/theme")

public class ThemeController {

    @Autowired
    private RepositoryTheme repository;

    @GetMapping
    public ResponseEntity<List<Theme>> getAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theme> getById(@PathVariable long id){
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Theme>> getByName(@PathVariable String name){
        return ResponseEntity.ok(repository.findAllByDescriptionContainingIgnoreCase(name));
    }

    @PostMapping
    public ResponseEntity<Theme> post (@RequestBody Theme theme){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(theme));
    }

    @PutMapping
    public ResponseEntity<Theme> put (@RequestBody Theme theme){
        return ResponseEntity.ok(repository.save(theme));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        repository.deleteById(id);
    }
}
