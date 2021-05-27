package blog.src.main.java.PersonalBlog.controller;

import blog.src.main.java.PersonalBlog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import blog.src.main.java.PersonalBlog.repository.PostsRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class PostController {

    @Autowired
    private PostsRepository repository;

    @GetMapping
    public ResponseEntity<List<Post>> GetAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> GetById(@PathVariable long id){
        return repository.findById(id)
        .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Post>> GetByTitulo(@PathVariable String title){
        return ResponseEntity.ok(repository.findAllByTitleContainingIgnoreCase(title));
    }

    @PostMapping
    public ResponseEntity<Post> post (@RequestBody Post post){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(post));
    }

    @PutMapping
    public ResponseEntity<Post> put (@RequestBody Post post){
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(post));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        repository.deleteById(id);
    }

}
