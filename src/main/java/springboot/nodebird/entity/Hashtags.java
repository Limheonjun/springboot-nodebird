package springboot.nodebird.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Hashtags {

    @Id @GeneratedValue
    @Column(name = "hashtag_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "hashtags")
    private List<PostsHashtags> postsHashtagsList = new ArrayList<>();
}
