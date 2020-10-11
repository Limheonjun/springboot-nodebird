package springboot.nodebird.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.nodebird.entity.Posts;

public interface PostRepository extends JpaRepository<Posts, Long> {
}
