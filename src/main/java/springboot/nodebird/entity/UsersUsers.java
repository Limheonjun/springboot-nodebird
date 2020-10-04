package springboot.nodebird.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "FOLLOW")
@Data
public class UsersUsers {

    @Id @GeneratedValue
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private Users followings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private Users followers;
}
