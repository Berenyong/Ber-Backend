package bssm.ber.web.dto.posts.posts.request;

import bssm.ber.domain.posts.posts.ManagerPosts;
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
