package springboot.nodebird.dto;

import lombok.Getter;
import lombok.Setter;
import springboot.nodebird.entity.UsersPosts;

@Getter
@Setter
public class LikedDTO {

    private Long id;
    private Long userId;
    private Long postId;

    public LikedDTO(UsersPosts usersPosts) {
        this.id = usersPosts.getId();
        this.userId = usersPosts.getUsers().getId();
        this.postId = usersPosts.getPosts().getId();
    }
}
