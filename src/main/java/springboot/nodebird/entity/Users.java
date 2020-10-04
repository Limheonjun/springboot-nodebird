package springboot.nodebird.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Users {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;
}
