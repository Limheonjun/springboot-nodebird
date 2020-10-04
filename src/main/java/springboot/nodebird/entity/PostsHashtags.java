package springboot.nodebird.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PostsHashtags {

    @Id @GeneratedValue
    @Column(name = "posthashtag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Posts posts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private Hashtags hashtags;
}
