package bssm.ber.service.posts;

import bssm.ber.web.dto.posts.ShareMajorPostsCreateRequestDto;
import bssm.ber.web.dto.posts.ShareMajorPostsResponseDto;

import java.util.List;

public interface ShareMajorPostsService {

    Long create(ShareMajorPostsCreateRequestDto request);

    List<ShareMajorPostsResponseDto> findByTitle(String title);

    List<ShareMajorPostsResponseDto> all();

    ShareMajorPostsResponseDto detail(Long id);

    Long update(Long id, ShareMajorPostsCreateRequestDto request);

    Long delete(Long id);
}
