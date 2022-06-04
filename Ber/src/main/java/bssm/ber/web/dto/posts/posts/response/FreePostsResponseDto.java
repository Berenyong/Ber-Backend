package bssm.ber.web.dto.posts.posts.response;

import bssm.ber.domain.entity.posts.posts.FreePosts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FreePostsResponseDto {

    private Long id;
    private String title;
    private String content;

    @Builder
    public FreePostsResponseDto(FreePosts freePosts) {
        this.id = freePosts.getId();
        this.title = freePosts.getTitle();
        this.content = freePosts.getContent();
    }
}
