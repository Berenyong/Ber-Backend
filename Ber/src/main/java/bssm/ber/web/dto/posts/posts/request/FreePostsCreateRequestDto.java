package bssm.ber.web.dto.posts.posts.request;

import bssm.ber.domain.posts.posts.FreePosts;
import bssm.ber.domain.users.Users;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FreePostsCreateRequestDto {

    private String title;
    private String content;
    private Users writer;

    public FreePosts toEntity() {
        return FreePosts.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }
}
