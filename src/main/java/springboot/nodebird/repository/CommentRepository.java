package springboot.nodebird.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.nodebird.entity.Comments;

public interface CommentRepository extends JpaRepository<Comments, Long> {
}
