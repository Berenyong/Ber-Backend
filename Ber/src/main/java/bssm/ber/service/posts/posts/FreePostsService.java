package bssm.ber.service.posts.posts;

import bssm.ber.web.dto.posts.posts.request.FreePostsCreateRequestDto;
import bssm.ber.web.dto.posts.posts.response.FreePostsResponseDto;

import java.util.List;

public interface FreePostsService {

    Long create(FreePostsCreateRequestDto request);

    List<FreePostsResponseDto> findByTitle(String title);

    List<FreePostsResponseDto> all();

    FreePostsResponseDto detail(Long id);

    Long update(Long id, FreePostsCreateRequestDto request);

    Long delete(Long id);
}
