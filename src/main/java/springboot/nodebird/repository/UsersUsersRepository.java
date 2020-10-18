package springboot.nodebird.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.nodebird.entity.Users;
import springboot.nodebird.entity.UsersUsers;

public interface UsersUsersRepository extends JpaRepository<UsersUsers, Long> {

    int deleteByFollowingsAndFollowers(Users a, Users b);
}
