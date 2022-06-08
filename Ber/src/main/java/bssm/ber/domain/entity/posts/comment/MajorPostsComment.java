package bssm.ber.domain.entity.posts.comment;

import bssm.ber.domain.entity.posts.posts.FreePosts;
import bssm.ber.domain.entity.posts.posts.MajorPosts;
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
public class MajorPostsComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    private boolean isRemoved= false;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "majorPosts_id")
    private MajorPosts majorPosts;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "users_id")
    private Users writer;

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private MajorPostsComment parent;

    @OneToMany(mappedBy = "parent")
    private List<MajorPostsComment> childList = new ArrayList<>();

    public void updateComment(String comment) {
        this.comment = comment;
    }

    //== 연관관계 편의 메서드 ==//
    public void confirmWriter(Users writer) {
        this.writer = writer;
        writer.addComment(this);
    }

    public void confirmPost(MajorPosts post) {
        this.majorPosts = post;
        post.addComment(this);
    }

    public void confirmParent(MajorPostsComment parent){
        this.parent = parent;
        parent.addChild(this);
    }

    public void addChild(MajorPostsComment child){
        childList.add(child);
    }

}