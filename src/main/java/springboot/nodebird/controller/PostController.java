package springboot.nodebird.controller;

import org.springframework.web.bind.annotation.*;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostController {

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
