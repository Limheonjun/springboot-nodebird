package springboot.nodebird.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
