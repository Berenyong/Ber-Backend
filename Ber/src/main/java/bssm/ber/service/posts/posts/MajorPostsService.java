package bssm.ber.service.posts.posts;

import bssm.ber.web.dto.posts.posts.request.MajorPostsCreateRequestDto;
import bssm.ber.web.dto.posts.posts.response.MajorPostsResponseDto;

import java.util.List;

public interface MajorPostsService {

    Long create(MajorPostsCreateRequestDto request);

    List<MajorPostsResponseDto> findByTitle(String title);

    List<MajorPostsResponseDto> all();

    MajorPostsResponseDto detail(Long id);

    Long update(Long id, MajorPostsCreateRequestDto request);

    Long delete(Long id);
}
