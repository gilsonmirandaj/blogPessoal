package blog.src.main.java.PersonalBlog.repository;

import blog.src.main.java.PersonalBlog.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RepositoryTheme extends JpaRepository<Theme, Long> {
    public List<Theme> findAllByDescriptionContainingIgnoreCase(String description);
}
