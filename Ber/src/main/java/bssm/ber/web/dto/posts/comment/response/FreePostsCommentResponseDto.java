package bssm.ber.web.dto.posts.comment.response;

import bssm.ber.domain.entity.posts.comment.FreePostsComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class FreePostsCommentResponseDto {
    private Long id;
    private String comment;
    private String nickname;
    private Long postsId;

    // Entity -> Dto
    public FreePostsCommentResponseDto(FreePostsComment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        // getUsers 가 동작하지 않습니다.
        // -> FreePostsCommentRequestDto 에서 Users 를 Entity 로 변환해야 합니다.
        this.nickname = comment.getWriter().getNickname();
        this.postsId = comment.getFreePosts().getId();
    }
}
