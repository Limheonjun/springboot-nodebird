package springboot.nodebird.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.nodebird.dto.UserDTO;
import springboot.nodebird.entity.Users;
import springboot.nodebird.entity.UsersUsers;
import springboot.nodebird.repository.UserRepository;
import springboot.nodebird.repository.UsersUsersRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

import static org.springframework.security.crypto.bcrypt.BCrypt.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UsersUsersRepository usersUsersRepository;

    @GetMapping("/")
    public Object keepLogIn(HttpSession session, HttpServletResponse response) {
        Users users = (Users) session.getAttribute("userId");
        if(users == null) {
            response.setStatus(HttpServletResponse.SC_OK);
            return null;
        }

        response.setStatus(HttpServletResponse.SC_OK);
        return new UserDTO(users);
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
        UserDTO usersDTO = new UserDTO(findUsers);
        response.setStatus(HttpServletResponse.SC_OK);
        return usersDTO;
    }

    @PostMapping("/logout")
    public Object logIn(HttpSession session, HttpServletResponse response) throws ServletException {
        session.invalidate();
        response.setStatus(HttpServletResponse.SC_OK);
        return "ok";
    }

    @PatchMapping("/nickname")
    public String changeNickname(@RequestBody Map<String, String> m, HttpSession session, HttpServletResponse response){
        Long userId = ((Users) session.getAttribute("userId")).getId();
        Users findUsers = userRepository.findById(userId).get();
        findUsers.setNickname(m.get("nickname"));
        userRepository.save(findUsers);
        response.setStatus(HttpServletResponse.SC_OK);
        return m.get("nickname");
    }

    @PatchMapping("/{userId}/follow")
    public Object follow(@PathVariable Long userId, HttpSession session, HttpServletResponse response){
        Optional<Users> optinal = userRepository.findById(userId);
        if(optinal.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return "없는 사람을 팔로우 하려고 하시네요?";
        }

        Users findUsers = optinal.get(); //팔로우 하고자 하는 사람
        usersUsersRepository.save(new UsersUsers(null, findUsers, (Users) session.getAttribute("userId")));
        response.setStatus(HttpServletResponse.SC_OK);
        return userId;
    }

    @DeleteMapping("/{userId}/follow")
    @Transactional
    public Object unfollow(@PathVariable Long userId, HttpSession session, HttpServletResponse response){
        Optional<Users> optinal = userRepository.findById(userId);
        if(optinal.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return "없는 사람을 언팔로우 하려고 하시네요?";
        }

        Users findUsers = optinal.get(); //언팔로우 하고자 하는 사람
        usersUsersRepository.deleteByFollowingsAndFollowers(findUsers, (Users) session.getAttribute("userId"));
        response.setStatus(HttpServletResponse.SC_OK);
        return userId;
    }
}


