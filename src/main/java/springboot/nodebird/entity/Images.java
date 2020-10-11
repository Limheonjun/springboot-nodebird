package springboot.nodebird.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Images {

    @Id @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    @Column(nullable = false)
    private String src;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Posts posts;
}
