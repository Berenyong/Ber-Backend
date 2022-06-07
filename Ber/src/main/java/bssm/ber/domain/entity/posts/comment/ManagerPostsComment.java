package bssm.ber.domain.entity.posts.comment;

import bssm.ber.domain.entity.posts.posts.MajorPosts;
import bssm.ber.domain.entity.posts.posts.ManagerPosts;
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
public class ManagerPostsComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    private boolean isRemoved= false;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "managerPosts_id")
    private ManagerPosts managerPosts;

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
    private ManagerPostsComment parent;

    @OneToMany(mappedBy = "parent")
    private List<ManagerPostsComment> childList = new ArrayList<>();

    public void updateComment(String comment) {
        this.comment = comment;
    }

    //== 연관관계 편의 메서드 ==//
    public void confirmWriter(Users writer) {
        this.writer = writer;
        writer.addComment(this);
    }

    public void confirmPost(ManagerPosts post) {
        this.managerPosts = post;
        post.addComment(this);
    }

    public void confirmParent(ManagerPostsComment parent){
        this.parent = parent;
        parent.addChild(this);
    }

    public void addChild(ManagerPostsComment child){
        childList.add(child);
    }

}
