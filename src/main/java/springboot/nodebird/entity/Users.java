package springboot.nodebird.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "users")
    private List<Posts> postsList = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<Comments> commentsList = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<UsersPosts> usersPostsList = new ArrayList<>();

    @OneToMany(mappedBy = "followings")
    private List<UsersUsers> followings = new ArrayList<>();

    @OneToMany(mappedBy = "followers")
    private List<UsersUsers> followers = new ArrayList<>();
}
