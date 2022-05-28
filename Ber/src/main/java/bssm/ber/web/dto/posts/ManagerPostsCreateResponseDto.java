package bssm.ber.web.dto.posts;

import bssm.ber.domain.entity.posts.ManagerPosts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ManagerPostsCreateResponseDto {

    private Long id;
    private String title;
    private String content;

    @Builder
    public ManagerPostsCreateResponseDto(ManagerPosts managerPosts) {
        this.id = managerPosts.getId();
        this.title = managerPosts.getTitle();
        this.content = managerPosts.getContent();
    }

}
