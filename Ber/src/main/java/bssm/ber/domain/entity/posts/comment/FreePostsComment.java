package bssm.ber.domain.entity.posts.comment;

import bssm.ber.domain.entity.posts.posts.FreePosts;
import bssm.ber.domain.entity.users.Users;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static javax.persistence.FetchType.LAZY;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class FreePostsComment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    private boolean isRemoved= false;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "freePosts_id")
    private FreePosts freePosts;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private FreePostsComment parent;

}
