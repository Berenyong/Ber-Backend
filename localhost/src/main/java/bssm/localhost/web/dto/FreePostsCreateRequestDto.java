package bssm.localhost.web.dto;

import bssm.localhost.domain.entity.FreePosts;
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
