package bssm.ber.web.dto.posts.posts.request;

import bssm.ber.domain.posts.posts.ShareMajorPosts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ShareMajorPostsCreateRequestDto {

    private String title;
    private String content;

    public ShareMajorPosts toEntity(){
        return ShareMajorPosts.builder()
                .title(title)
                .content(content)
                .build();
    }
}
