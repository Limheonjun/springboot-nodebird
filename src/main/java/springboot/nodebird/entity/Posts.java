package springboot.nodebird.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Posts {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String content;
}
