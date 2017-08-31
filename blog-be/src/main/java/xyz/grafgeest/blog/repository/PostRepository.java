package xyz.grafgeest.blog.repository;

import xyz.grafgeest.blog.entity.Post;

/**
 * Spring Data JPA repository for the Post entity.
 */
public interface PostRepository extends GenericRepository<Post> {
    boolean existsByTitle(String title);
}
