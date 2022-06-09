package bssm.ber.web.dto.posts.posts.request;

import bssm.ber.domain.posts.posts.MajorPosts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MajorPostsCreateRequestDto {

    private String title;
    private String content;

    public MajorPosts toEntity(){
        return MajorPosts.builder()
                .title(title)
                .content(content)
                .build();
    }
}
