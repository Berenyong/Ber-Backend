package bssm.ber.web.dto.posts.posts.response;

import bssm.ber.domain.posts.posts.ShareMajorPosts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShareMajorPostsResponseDto {

    private Long id;
    private String title;
    private String content;

    @Builder
    public ShareMajorPostsResponseDto(ShareMajorPosts shareMajorPosts) {
        this.id = shareMajorPosts.getId();
        this.title = shareMajorPosts.getTitle();
        this.content = shareMajorPosts.getContent();
    }

}
