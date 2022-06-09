package bssm.ber.web.dto.posts.comment.request;

import bssm.ber.domain.posts.comment.ShareMajorPostsComment;
import bssm.ber.domain.posts.posts.ShareMajorPosts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShareMajorPostsCommentRequestDto {

    private Long id;
    private String comment;
    private ShareMajorPosts shareMajorPosts;

    public ShareMajorPostsComment toEntity() {
        return ShareMajorPostsComment.builder()
                .id(id)
                .comment(comment)
                .shareMajorPosts(shareMajorPosts)
                .build();
    }
}
