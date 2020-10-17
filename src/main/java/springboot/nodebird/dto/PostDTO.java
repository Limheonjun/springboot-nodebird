package springboot.nodebird.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import springboot.nodebird.entity.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostDTO {

    private Long id;
    private String content;
    private User users;
    private List<CommentDTO> commentsList = new ArrayList<>();
    private List<Long> imagesList = new ArrayList<>();
    private List<Long> postsHashtagsList = new ArrayList<>();
    private List<LikedDTO> likers = new ArrayList<>();
    //private Long retweet;
    private List<Long> retweetList = new ArrayList<>();

    public PostDTO(Posts posts) {
        this.id = posts.getId();
        this.content = posts.getContent();
        this.users = new User(posts.getUsers().getId(), posts.getUsers().getNickname());
        for(Comments comments : posts.getCommentsList()) {
            this.commentsList.add(new CommentDTO(comments));
        }
        for(Images images : posts.getImagesList()) {
            this.imagesList.add(images.getId());
        }
        for(PostsHashtags postsHashtagsList : posts.getPostsHashtagsList()) {
            this.postsHashtagsList.add(postsHashtagsList.getId());
        }
        for(UsersPosts usersPosts : posts.getUsersPostsList()) {
            this.likers.add(new LikedDTO(usersPosts));
        }
        //this.retweet = posts.getRetweet().getId();
        for(Posts post : posts.getRetweetList()) {
            this.retweetList.add(post.getId());
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private class User {
        private Long id;
        private String nickname;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private class Post {
        private Long id;
        private String nickname;
    }
}
