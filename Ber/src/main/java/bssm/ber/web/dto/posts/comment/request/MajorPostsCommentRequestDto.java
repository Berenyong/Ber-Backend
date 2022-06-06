package bssm.ber.web.dto.posts.comment.request;

import bssm.ber.domain.entity.posts.comment.FreePostsComment;
import bssm.ber.domain.entity.posts.comment.MajorPostsComment;
import bssm.ber.domain.entity.posts.posts.FreePosts;
import bssm.ber.domain.entity.posts.posts.MajorPosts;
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
public class MajorPostsCommentRequestDto {

    private Long id;
    private String comment;
    private String createdDate
            = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String modifiedDate
            = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private MajorPosts majorPosts;

    public MajorPostsComment toEntity() {
        return MajorPostsComment.builder()
                .id(id)
                .comment(comment)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .majorPosts(majorPosts)
                .build();
    }
}
