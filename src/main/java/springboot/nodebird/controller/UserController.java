package springboot.nodebird.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.nodebird.entity.Users;
import springboot.nodebird.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

import static org.springframework.security.crypto.bcrypt.BCrypt.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/")
    public String createUser(@RequestBody Map<String, String> m, HttpServletResponse response) {
        Users findUsers = userRepository.findByEmail(m.get("email"));
        if(findUsers != null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); //403
            return "이미 사용중인 아이디 입니다";
        }

        String passwordHashed = hashpw(m.get("password"), gensalt());
        Users users = Users.builder()
                .email(m.get("email"))
                .nickname(m.get("nickname"))
                .password(passwordHashed)
                .build();
        System.out.println("passwordHashed : " + passwordHashed);
        userRepository.save(users);
        return "ok";
    }

    @PostMapping("/login")
    public String logIn(@RequestBody Map<String, String> m, HttpServletResponse response) {
        Users findUsers = userRepository.findByEmail(m.get("email"));
        if(findUsers == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "존재하지 않는 아이디 입니다";
        }

        String passwordHashed = hashpw(m.get("password"), gensalt());
        if(!checkpw(m.get("password"), findUsers.getPassword())){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return "비밀번호가 일치하지 않습니다";
        }

        response.setStatus(HttpServletResponse.SC_OK);
        return "ok";
    }
}