package bssm.ber.domain.entity.posts.posts;

import bssm.ber.domain.BaseTimeEntity;
import bssm.ber.domain.entity.posts.comment.FreePostsComment;
import bssm.ber.domain.entity.posts.comment.MajorPostsComment;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MajorPosts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "majorPosts_id")
    private Long id;

    @NotNull
    @Column(length = 100)
    private String title;

    @NotNull
    @Column(columnDefinition = "TEXT", length = 2000)
    private String content;

    @OneToMany(mappedBy = "majorPosts", cascade = CascadeType.REMOVE)
    private List<MajorPostsComment> majorPostsComments;


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
