package bssm.ber.web.dto.posts.comment.response;

import bssm.ber.domain.posts.comment.MajorPostsComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MajorPostsCommentResponseDto {
    private Long id;
    private String comment;
    private String nickname;
    private Long postsId;
    private LocalDateTime createdDate;

    // Entity -> Dto
    public MajorPostsCommentResponseDto(MajorPostsComment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        // getUsers 가 동작하지 않습니다.
        // -> FreePostsCommentRequestDto 에서 Users 를 Entity 로 변환해야 합니다.
        this.nickname = comment.getWriter().getNickname();
        this.postsId = comment.getMajorPosts().getId();
        this.createdDate = comment.getCreatedDate();
    }

}
