package blog.src.main.java.PersonalBlog.repository;

import blog.src.main.java.PersonalBlog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    public Optional<User> findByUser(String user);
}