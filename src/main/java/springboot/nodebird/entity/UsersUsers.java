package springboot.nodebird.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "FOLLOW")
@Getter
@Setter
public class UsersUsers extends BaseTimeEntity{

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
