package bssm.ber.domain.posts.posts;

import bssm.ber.domain.BaseTimeEntity;
import bssm.ber.domain.posts.comment.FreePostsComment;
import bssm.ber.domain.users.Users;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;
import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FreePosts extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "freePosts_id")
    private Long id;

    @NotNull
    @Column(length = 100)
    private String title;

    @NotNull
    @Column(columnDefinition = "TEXT", length = 2000)
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "users_id")
    private Users writer;

    // 양방향 연관관계이며, 게시글 삭제시 댓글 또한 삭제되어야 하기 때문에 CascadeType.REMOVE 사용합니다.
    @OneToMany(mappedBy = "freePosts", cascade = CascadeType.REMOVE)
    private List<FreePostsComment> freePostsComments = new ArrayList<>();

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //== 연관관계 편의 메서드 ==//
    public void confirmWriter(Users writer) {
        this.writer = writer;
        writer.addFreePost(this);
    }

    public void addComment(FreePostsComment comment){
        // comment 의 Post 설정은 comment 에서 합니다.
        freePostsComments.add(comment);
    }

}
