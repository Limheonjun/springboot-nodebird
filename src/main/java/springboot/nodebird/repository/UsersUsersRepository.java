package springboot.nodebird.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.nodebird.entity.Users;
import springboot.nodebird.entity.UsersUsers;

import java.util.List;

public interface UsersUsersRepository extends JpaRepository<UsersUsers, Long> {

    int deleteByFollowingsAndFollowers(Users a, Users b);

    List<UsersUsers> findByFollowers(Users users);

    List<UsersUsers> findByFollowings(Users users);
}
