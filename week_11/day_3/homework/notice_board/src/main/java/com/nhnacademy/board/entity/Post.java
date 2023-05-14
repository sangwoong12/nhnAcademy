package com.nhnacademy.board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "PostWithUser", attributeNodes = {
                @NamedAttributeNode("user")
        })
})
@Entity
@Table(name = "Post")
@Setter
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "writer_user_id", referencedColumnName = "user_id")
    private User user;
    @Column(name = "write_time")
    private LocalDateTime writeTime;
    @Column(name = "view_count")
    private int viewCount;
}
