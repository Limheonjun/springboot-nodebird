package springboot.nodebird.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.nodebird.dto.CommentDTO;
import springboot.nodebird.dto.LikedDTO;
import springboot.nodebird.dto.PostDTO;
import springboot.nodebird.entity.Comments;
import springboot.nodebird.entity.Posts;
import springboot.nodebird.entity.Users;
import springboot.nodebird.entity.UsersPosts;
import springboot.nodebird.repository.CommentRepository;
import springboot.nodebird.repository.PostRepository;
import springboot.nodebird.repository.UserRepository;
import springboot.nodebird.repository.UsersPostsRepository;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
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

    @Autowired
    UsersPostsRepository usersPostsRepository;

    @PostMapping("/")
    public Object createPost(@RequestBody Map<String, Object> m, HttpSession session, HttpServletResponse response) {

        try {
            Posts posts = new Posts();
            posts.setContent((String) m.get("content"));
            Users users = (Users) session.getAttribute("userId");
            posts.setUsers(userRepository.findById(users.getId()).get());
            postRepository.save(posts);
            Posts findPost = postRepository.findById(posts.getId()).get();
            PostDTO postsDTO = new PostDTO(findPost);
            response.setStatus(HttpServletResponse.SC_CREATED);
            return postsDTO;
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>> e : " + e.getMessage());
            throw e;
        }
    }

    @PostMapping("/{postId}/comment")
    public Object createComment(@PathVariable Long postId, @RequestBody Map<String, Object> m, HttpSession session, HttpServletResponse response) {
        System.out.println(">>>>>>>>>>>>>>>>>> id : " + postId);
        System.out.println(">>>>>>>>>>>>>>>>>> m : " + m.toString());
        Posts findPosts = postRepository.findById(postId).get();
        Long id = new Long((Integer)m.get("userId"));
        Users findUsers = userRepository.findById(id).get();

        Comments comments = new Comments();
        comments.setContent((String) m.get("content"));
        comments.setPosts(findPosts);
        comments.setUsers((Users) session.getAttribute("userId"));
        commentRepository.save(comments);
        return new CommentDTO(comments);
    }

    @PatchMapping("/{postId}/like")
    public Object likePost(@PathVariable Long postId, HttpServletResponse response, HttpSession session) {
        Optional<Posts> findPosts = postRepository.findById(postId);
        if(findPosts.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return "게시글이 존재하지 않습니다";
        }

        UsersPosts usersPosts = new UsersPosts();
        usersPosts.setUsers((Users) session.getAttribute("userId"));
        usersPosts.setPosts(findPosts.get());
        usersPostsRepository.save(usersPosts);
        return new LikedDTO(usersPosts);

    }

    @DeleteMapping("/{postId}/like")
    @Transactional//delete를 하는 곳에는 무조건 필요
    public Object unlikePost(@PathVariable Long postId, HttpServletResponse response, HttpSession session) {
        Optional<Posts> findPosts = postRepository.findById(postId);
        if(findPosts.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return "게시글이 존재하지 않습니다";
        }

        UsersPosts usersPosts = new UsersPosts();
        usersPosts.setUsers((Users) session.getAttribute("userId"));
        usersPosts.setPosts(findPosts.get());
        usersPostsRepository.deleteByPostsIdAndUsersId(findPosts.get().getId(), ((Users) session.getAttribute("userId")).getId());
        return new LikedDTO(usersPosts);
    }

    @DeleteMapping("/{postId}")
    @Transactional
    public Long deletePost(@PathVariable Long postId) {
        Posts findPosts = postRepository.findById(postId).get();
        postRepository.delete(findPosts);
        return postId;
    }
}
