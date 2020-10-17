package springboot.nodebird.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.nodebird.entity.Posts;
import springboot.nodebird.entity.Users;
import springboot.nodebird.entity.UsersPosts;

public interface UsersPostsRepository extends JpaRepository<UsersPosts, Long> {

    int deleteByPostsIdAndUsersId(Long postsId, Long usersId);
}
