/*dataBase communication*/

package blog.src.main.java.PersonalBlog.repository;

import blog.src.main.java.PersonalBlog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {
    public List<Post> findAllByTitleContainingIgnoreCase(String title);
}
