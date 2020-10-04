package springboot.nodebird.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Hashtags {

    @Id @GeneratedValue
    @Column(name = "hashtag_id")
    private Long id;

    @Column(nullable = false)
    private String name;
}
