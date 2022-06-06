package bssm.ber.web.dto.posts.comment.response;

import bssm.ber.domain.entity.posts.comment.FreePostsComment;
import bssm.ber.domain.entity.posts.comment.MajorPostsComment;
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
public class MajorPostsCommentResponseDto {
    private Long id;
    private String comment;
    private String createdDate
            = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String modifiedDate
            = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String nickname;
    private Long postsId;

    // Entity -> Dto
    public MajorPostsCommentResponseDto(MajorPostsComment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
        // getUsers 가 동작하지 않습니다.
        // -> FreePostsCommentRequestDto 에서 Users 를 Entity 로 변환해야 합니다.
        this.nickname = comment.getUsers().getNickname();
        this.postsId = comment.getMajorPosts().getId();
    }

}
