package springboot.nodebird.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "LIKED")
@Data
public class UsersPosts {

    @Id @GeneratedValue
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Posts posts;

}
