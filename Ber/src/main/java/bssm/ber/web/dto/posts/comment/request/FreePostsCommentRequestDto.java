package bssm.ber.web.dto.posts.comment.request;

import bssm.ber.domain.entity.posts.comment.FreePostsComment;
import bssm.ber.domain.entity.posts.posts.FreePosts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FreePostsCommentRequestDto {

    private Long id;
    private String comment;
    private FreePosts freePosts;

    // Dto -> Entity
    // Users 값을 받아서 Entity 로 변환해야 합니다.
    public FreePostsComment toEntity() {
        return FreePostsComment.builder()
                .id(id)
                .comment(comment)
                .freePosts(freePosts)
                .build();
    }
}
