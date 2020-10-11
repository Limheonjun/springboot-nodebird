package springboot.nodebird.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.nodebird.dto.PostsDTO;
import springboot.nodebird.entity.Comments;
import springboot.nodebird.entity.Posts;
import springboot.nodebird.entity.Users;
import springboot.nodebird.repository.CommentRepository;
import springboot.nodebird.repository.PostRepository;
import springboot.nodebird.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/")
    public Object createPost(@RequestBody Map<String, Object> m, HttpSession session, HttpServletResponse response) {

        try {
            Posts posts = new Posts();
            posts.setContent((String) m.get("content"));
            Users users = (Users) session.getAttribute("userId");
            posts.setUsers(userRepository.findById(users.getId()).get());
            postRepository.save(posts);
            Posts findPost = postRepository.findById(posts.getId()).get();
            PostsDTO postsDTO = new PostsDTO(findPost);
            response.setStatus(HttpServletResponse.SC_CREATED);
            return postsDTO;
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>> e : " + e.getMessage());
            throw e;
        }
    }

    @PostMapping("/{postId}/comment")
    public void createComment(@PathVariable Long postId, Map<String, Object> m, HttpSession session, HttpServletResponse response) {
        System.out.println(">>>>>>>>>>>>>>>>>> id : " + postId);
        System.out.println(">>>>>>>>>>>>>>>>>> m : " + m.toString());
        Posts findPosts = postRepository.findById(postId).get();

        Comments comments = new Comments();
        comments.setContent((String) m.get("content"));
        comments.setPosts(findPosts);
        comments.setUsers((Users) session.getAttribute("userId"));
        commentRepository.save(comments);
    }

    @PostMapping("/post")
    public Map<String, Object> postPost() {
        Map<String, Object> m = new HashMap<>();
        m.put("id", 1);
        m.put("content", "hello");
        return m;
    }

    @DeleteMapping("/post")
    public Map<String, Object> deletePost() {
        Map<String, Object> m = new HashMap<>();
        m.put("id", 1);
        return m;
    }
}
