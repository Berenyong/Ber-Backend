package bssm.ber.domain.entity.posts.posts;

import bssm.ber.domain.BaseTimeEntity;
import bssm.ber.domain.entity.posts.comment.FreePostsComment;
import bssm.ber.domain.entity.users.Users;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", insertable = false, updatable = false)
    private Users users;

    // 양방향 연관관계이며, 게시글 삭제시 댓글 또한 삭제되어야 하기 때문에 CascadeType.REMOVE 사용합니다.
    @OneToMany(mappedBy = "freePosts", cascade = CascadeType.REMOVE)
    private List<FreePostsComment> freePostsComments = new ArrayList<>();

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //== 연관관계 편의 메서드 ==//
    public void confirmWriter(Users writer) {
        // writer 는 변경이 불가능하므로 이렇게만 해주겠습니다.
        this.users = writer;
        writer.addPost(this);
    }

    public void addComment(FreePostsComment comment){
        // comment 의 Post 설정은 comment에서 합니다.
        freePostsComments.add(comment);
    }


}
