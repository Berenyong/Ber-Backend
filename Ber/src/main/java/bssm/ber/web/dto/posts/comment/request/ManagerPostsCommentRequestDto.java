package bssm.ber.web.dto.posts.comment.request;

import bssm.ber.domain.entity.posts.comment.ManagerPostsComment;
import bssm.ber.domain.entity.posts.posts.ManagerPosts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerPostsCommentRequestDto {

    private Long id;
    private String comment;
    private ManagerPosts managerPosts;

    public ManagerPostsComment toEntity() {
        return ManagerPostsComment.builder()
                .id(id)
                .comment(comment)
                .managerPosts(managerPosts)
                .build();
    }
}
