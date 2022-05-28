package bssm.ber.web.dto.posts;

import bssm.ber.domain.entity.posts.FreePosts;
import bssm.ber.domain.entity.posts.ManagerPosts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ManagerPostsCreateRequestDto {

    private String title;
    private String content;

    public ManagerPosts toEntity(){
        return ManagerPosts.builder()
                .title(title)
                .content(content)
                .build();
    }
}
