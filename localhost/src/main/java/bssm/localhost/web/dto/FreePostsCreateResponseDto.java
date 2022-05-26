package bssm.localhost.web.dto;

import bssm.localhost.domain.entity.FreePosts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FreePostsCreateResponseDto {

    private Long id;
    private String title;
    private String content;

    public FreePostsCreateResponseDto(FreePosts freePosts) {
        this.id = freePosts.getId();
        this.title = freePosts.getTitle();
        this.content = freePosts.getContent();
    }
}
