package springboot.nodebird.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import springboot.nodebird.entity.Comments;
import springboot.nodebird.entity.Posts;
import springboot.nodebird.entity.Users;

import javax.persistence.*;

@Getter
@Setter
public class CommentDTO {

    private Long id;
    private String content;
    private User user;
    private Post post;

    public CommentDTO (Comments comments) {
        this.id = comments.getId();
        this.content = comments.getContent();
        this.user = new User(comments.getUsers().getId(), comments.getUsers().getNickname());
        this.post = new Post(comments.getPosts().getId(), comments.getPosts().getContent());
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
