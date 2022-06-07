package bssm.ber.domain.entity.posts.comment;

import bssm.ber.domain.BaseTimeEntity;
import bssm.ber.domain.entity.posts.posts.FreePosts;
import bssm.ber.domain.entity.posts.posts.MajorPosts;
import bssm.ber.domain.entity.posts.posts.ShareMajorPosts;
import bssm.ber.domain.entity.users.Users;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ShareMajorPostsComment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    private boolean isRemoved= false;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "freePosts_id")
    private ShareMajorPosts shareMajorPosts;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "users_id")
    private Users writer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private ShareMajorPostsComment parent;

    //== 부모 댓글을 삭제해도 자식 댓글은 남아있습니다. ==//
    @OneToMany(mappedBy = "parent")
    private List<ShareMajorPostsComment> childList = new ArrayList<>();

    public void updateComment(String comment) {
        this.comment = comment;
    }

    //== 연관관계 편의 메서드 ==//
    public void confirmWriter(Users writer) {
        this.writer = writer;
        writer.addComment(this);
    }

    public void confirmPost(ShareMajorPosts post) {
        this.shareMajorPosts = post;
        post.addComment(this);
    }

    public void confirmParent(ShareMajorPostsComment parent){
        this.parent = parent;
        parent.addChild(this);
    }

    public void addChild(ShareMajorPostsComment child){
        childList.add(child);
    }

}
