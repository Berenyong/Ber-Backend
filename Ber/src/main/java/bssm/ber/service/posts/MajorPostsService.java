package bssm.ber.service.posts;

import bssm.ber.web.dto.posts.MajorPostsCreateRequestDto;
import bssm.ber.web.dto.posts.MajorPostsResponseDto;

import java.util.List;

public interface MajorPostsService {

    Long create(MajorPostsCreateRequestDto request);

    List<MajorPostsResponseDto> findByTitle(String title);

    List<MajorPostsResponseDto> all();

    MajorPostsResponseDto detail(Long id);

    Long update(Long id, MajorPostsCreateRequestDto request);

    Long delete(Long id);
}
