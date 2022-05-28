package bssm.ber.service.posts;

import bssm.ber.web.dto.posts.FreePostsCreateRequestDto;
import bssm.ber.web.dto.posts.FreePostsResponseDto;

import java.util.List;

public interface FreePostsService {

    Long create(FreePostsCreateRequestDto request);

    List<FreePostsResponseDto> findByTitle(String title);

    List<FreePostsResponseDto> all();

    FreePostsResponseDto detail(Long id);

    Long update(Long id, FreePostsCreateRequestDto request);

    Long delete(Long id);
}
