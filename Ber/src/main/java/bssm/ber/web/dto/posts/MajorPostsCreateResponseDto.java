package bssm.ber.web.dto.posts;

import bssm.ber.domain.entity.posts.MajorPosts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MajorPostsCreateResponseDto {

    private Long id;
    private String title;
    private String content;

    @Builder
    public MajorPostsCreateResponseDto(MajorPosts majorPosts) {
        this.id = majorPosts.getId();
        this.title = majorPosts.getTitle();
        this.content = majorPosts.getContent();
    }
}