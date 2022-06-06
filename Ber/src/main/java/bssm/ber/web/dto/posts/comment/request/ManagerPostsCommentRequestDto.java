package bssm.ber.web.dto.posts.comment.request;

import bssm.ber.domain.entity.posts.comment.MajorPostsComment;
import bssm.ber.domain.entity.posts.comment.ManagerPostsComment;
import bssm.ber.domain.entity.posts.posts.MajorPosts;
import bssm.ber.domain.entity.posts.posts.ManagerPosts;
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
public class ManagerPostsCommentRequestDto {

    private Long id;
    private String comment;
    private String createdDate
            = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String modifiedDate
            = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private ManagerPosts managerPosts;

    public ManagerPostsComment toEntity() {
        return ManagerPostsComment.builder()
                .id(id)
                .comment(comment)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .managerPosts(managerPosts)
                .build();
    }
}
