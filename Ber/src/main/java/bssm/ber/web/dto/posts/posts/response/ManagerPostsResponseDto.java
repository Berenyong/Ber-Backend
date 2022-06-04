package bssm.ber.web.dto.posts.posts.response;

import bssm.ber.domain.entity.posts.posts.ManagerPosts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ManagerPostsResponseDto {

    private Long id;
    private String title;
    private String content;

    @Builder
    public ManagerPostsResponseDto(ManagerPosts managerPosts) {
        this.id = managerPosts.getId();
        this.title = managerPosts.getTitle();
        this.content = managerPosts.getContent();
    }

}
