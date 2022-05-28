package bssm.ber.web.dto;

import bssm.ber.domain.entity.FreePosts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FreePostsCreateResponseDto {

    private Long id;
    private String title;
    private String content;

    @Builder
    public FreePostsCreateResponseDto(FreePosts freePosts) {
        this.id = freePosts.getId();
        this.title = freePosts.getTitle();
        this.content = freePosts.getContent();
    }
}
