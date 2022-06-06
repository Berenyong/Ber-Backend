package bssm.ber.web.dto.posts.posts.response;

import bssm.ber.domain.entity.posts.posts.MajorPosts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MajorPostsResponseDto {

    private Long id;
    private String title;
    private String content;

    @Builder
    public MajorPostsResponseDto(MajorPosts majorPosts) {
        this.id = majorPosts.getId();
        this.title = majorPosts.getTitle();
        this.content = majorPosts.getContent();
    }
}