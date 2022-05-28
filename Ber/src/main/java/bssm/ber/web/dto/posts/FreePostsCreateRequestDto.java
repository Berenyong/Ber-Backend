package bssm.ber.web.dto.posts;

import bssm.ber.domain.entity.posts.FreePosts;
import lombok.*;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FreePostsCreateRequestDto {

    private String title;
    private String content;

    public FreePosts toEntity(){
        return FreePosts.builder()
                .title(title)
                .content(content)
                .build();
    }
}
