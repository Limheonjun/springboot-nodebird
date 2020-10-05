package springboot.nodebird.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.nodebird.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
}
