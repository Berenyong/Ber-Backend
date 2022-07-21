package bssm.ber.web.dto.posts.comment.request;

import bssm.ber.domain.posts.comment.FreePostsComment;
import bssm.ber.domain.posts.posts.FreePosts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FreePostsCommentRequestDto {

    private String comment;
    private FreePosts freePosts;

    public FreePostsComment toEntity() {
        return FreePostsComment.builder()
                .comment(comment)
                .freePosts(freePosts)
                .build();
    }
}
