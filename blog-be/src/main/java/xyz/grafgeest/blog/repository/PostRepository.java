package xyz.grafgeest.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.grafgeest.blog.entity.Post;

/**
 * Spring Data JPA repository for the Post entity.
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    boolean existsByTitle(String title);
}
