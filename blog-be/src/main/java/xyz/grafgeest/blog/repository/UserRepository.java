package xyz.grafgeest.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import xyz.grafgeest.blog.entity.User;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
