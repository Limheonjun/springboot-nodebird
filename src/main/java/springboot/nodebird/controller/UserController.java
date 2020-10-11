package springboot.nodebird.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.nodebird.dto.UsersDTO;
import springboot.nodebird.entity.Users;
import springboot.nodebird.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

import static org.springframework.security.crypto.bcrypt.BCrypt.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public Object keepLogIn(HttpSession session, HttpServletResponse response) {
        Users users = (Users) session.getAttribute("userId");
        if(users == null) {
            response.setStatus(HttpServletResponse.SC_OK);
            return null;
        }

        response.setStatus(HttpServletResponse.SC_OK);
        return new UsersDTO(users);
    }

    @PostMapping("/")
    public String createUser(@RequestBody Map<String, String> m, HttpServletResponse response) {
        Users findUsers = userRepository.findByEmail(m.get("email"));
        if(findUsers != null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); //403
            return "이미 사용중인 아이디 입니다";
        }

        Users users = Users.builder()
                .email(m.get("email"))
                .nickname(m.get("nickname"))
                .password(hashpw(m.get("password"), gensalt()))
                .build();
        userRepository.save(users);
        return "ok";
    }

    @PostMapping("/login")
    public Object logIn(@RequestBody Map<String, String> m, HttpSession session, HttpServletResponse response) {
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

        session.setAttribute("userId", findUsers);
        UsersDTO usersDTO = new UsersDTO(findUsers);
        response.setStatus(HttpServletResponse.SC_OK);
        return usersDTO;
    }

    @PostMapping("/logout")
    public Object logIn(HttpSession session, HttpServletResponse response) throws ServletException {
        session.invalidate();
        response.setStatus(HttpServletResponse.SC_OK);
        return "ok";
    }

    @Data
    static class Result<T> {
        T data;

        Result (T t) {
            this.data = t;
        }
    }
}


