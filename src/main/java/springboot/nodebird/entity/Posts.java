package springboot.nodebird.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Posts {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(mappedBy = "posts")
    private List<Comments> commentsList = new ArrayList<>();

    @OneToMany(mappedBy = "posts")
    private List<Images> imagesList = new ArrayList<>();

    @OneToMany(mappedBy = "posts")
    private List<PostsHashtags> postsHashtagsList = new ArrayList<>();

    @OneToMany(mappedBy = "posts")
    private List<UsersPosts> usersPostsList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "retweet_id")
    private Posts retweet;

    @OneToMany(mappedBy = "retweet")
    private List<Posts> retweetList = new ArrayList<>();
}
