package springboot.nodebird.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import springboot.nodebird.entity.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String email;
    private String nickname;
    private String password;
    private List<Long> postsList = new ArrayList<>();
    private List<Comments> commentsList = new ArrayList<>();
    private List<Long> usersPostsList = new ArrayList<>();
    private List<Follow> followings = new ArrayList<>(); //내가 팔로우 한 사람
    private List<Follow> followers = new ArrayList<>(); // 나를 팔로우 한 사람

    public UserDTO(Users users) {
        this.id = users.getId();
        this.email = users.getEmail();
        this.nickname = users.getNickname();
        this.password = users.getPassword();
        for(Posts posts : users.getPostsList()) {
            postsList.add(posts.getId());
        }
        //나를 팔로우한 아이디 추가
        for(UsersUsers following : users.getFollowings()) {
            System.out.println("로그인한 id : " + id);
            System.out.println("following.getFollowers().getId() : " + following.getFollowers().getId());
            if(this.id == following.getFollowings().getId()) {
                this.followers.add(new Follow(following.getFollowers().getId()));
                System.out.println("내가 팔로우한 아이디 : " + following.getFollowings().getId());
            }
        }
        //내가 팔로우한 아이디 추가
        for(UsersUsers followers : users.getFollowers()) {
            System.out.println("로그인한 id : " + id);
            System.out.println("followers.getFollowings().getId() : " + followers.getFollowings().getId());
            if(this.id == followers.getFollowers().getId()) {
                this.followings.add(new Follow(followers.getFollowings().getId()));
                System.out.println("나를 팔로우한 아이디 : " + followers.getFollowers().getId());
            }
        }

        for(UsersPosts usersPosts : users.getUsersPostsList()) {
            this.usersPostsList.add(usersPosts.getId());
        }

    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class Follow {
        private Long id;
    }
}