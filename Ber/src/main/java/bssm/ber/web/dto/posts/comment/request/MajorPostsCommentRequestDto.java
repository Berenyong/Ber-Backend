package bssm.ber.web.dto.posts.comment.request;

import bssm.ber.domain.entity.posts.comment.MajorPostsComment;
import bssm.ber.domain.entity.posts.posts.MajorPosts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MajorPostsCommentRequestDto {

    private Long id;
    private String comment;
    private MajorPosts majorPosts;

    public MajorPostsComment toEntity() {
        return MajorPostsComment.builder()
                .id(id)
                .comment(comment)
                .majorPosts(majorPosts)
                .build();
    }
}
