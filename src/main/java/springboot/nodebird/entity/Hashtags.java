package springboot.nodebird.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Hashtags extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "hashtag_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "hashtags")
    private List<PostsHashtags> postsHashtagsList = new ArrayList<>();
}
