package springboot.nodebird.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import springboot.nodebird.entity.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostsDTO {

    private Long id;
    private String content;
    private User users;
    private List<Comments> commentsList = new ArrayList<>();
    private List<Images> imagesList = new ArrayList<>();
    private List<PostsHashtags> postsHashtagsList = new ArrayList<>();
    private List<UsersPosts> usersPostsList = new ArrayList<>();
    private Posts retweet;
    private List<Posts> retweetList = new ArrayList<>();

    public PostsDTO (Posts posts) {
        this.id = posts.getId();
        this.content = posts.getContent();
        this.users = new User(posts.getUsers().getId(), posts.getUsers().getNickname());
        this.commentsList = posts.getCommentsList();
        this.imagesList = posts.getImagesList();
        this.postsHashtagsList = posts.getPostsHashtagsList();
        this.usersPostsList = posts.getUsersPostsList();
        this.retweet = posts.getRetweet();
        this.retweetList = posts.getRetweetList();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private class User {
        private Long id;
        private String nickname;
    }
}
