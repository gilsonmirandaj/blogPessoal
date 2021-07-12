package blog.src.main.java.PersonalBlog.controller;

import blog.src.main.java.PersonalBlog.model.User;
import blog.src.main.java.PersonalBlog.model.UserLogin;
import blog.src.main.java.PersonalBlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserLogin> Authentication(@RequestBody Optional<UserLogin> user){
        return userService.
                Login(user).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/sigin")
    public ResponseEntity<User> Post(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.RegisterUser(user));
    }
}
