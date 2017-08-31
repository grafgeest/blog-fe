package xyz.grafgeest.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.grafgeest.blog.entity.GenericEntity;

public interface GenericRepository<T extends GenericEntity> extends JpaRepository<T, Long> {
}
