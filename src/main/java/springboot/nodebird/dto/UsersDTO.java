package springboot.nodebird.dto;

import lombok.Getter;
import lombok.Setter;
import springboot.nodebird.entity.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsersDTO {

    private Long id;
    private String email;
    private String nickname;
    private String password;
    private List<Long> postsList = new ArrayList<>();
    private List<Comments> commentsList = new ArrayList<>();
    private List<UsersPosts> usersPostsList = new ArrayList<>();
    private List<Long> followings = new ArrayList<>();
    private List<Long> followers = new ArrayList<>();

    public UsersDTO (Users users) {
        this.id = users.getId();
        this.email = users.getEmail();
        this.nickname = users.getNickname();
        this.password = users.getPassword();
        for(Posts posts : users.getPostsList()) {
            postsList.add(posts.getId());
        }
        for(UsersUsers following : users.getFollowings()) {
            this.followings.add(following.getId());
        }
        for(UsersUsers followers : users.getFollowers()) {
            this.followers.add(followers.getId());
        }
        this.usersPostsList = users.getUsersPostsList();

    }
}