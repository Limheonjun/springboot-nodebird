package springboot.nodebird.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import springboot.nodebird.dto.PostDTO;
import springboot.nodebird.entity.Posts;
import springboot.nodebird.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    PostRepository postRepository;

    @PostMapping("/")
    public Object getPosts() {
        PageRequest pageRequest = PageRequest.of(0,  10);
        Page<Posts> page = postRepository.findAll(pageRequest);
        List<PostDTO> results = page.stream().map(PostDTO::new).collect(Collectors.toList());
        System.out.println("results : " + results.toString());
        return results;
    }
}
