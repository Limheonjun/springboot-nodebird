package springboot.nodebird.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springboot.nodebird.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

    @EntityGraph(attributePaths = {"postsList", "followings", "followers"})
    Users findFetchJoinByEmail(String email);
}
