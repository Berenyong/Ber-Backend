package bssm.ber.service.posts.posts;

import bssm.ber.web.dto.posts.posts.request.ManagerPostsCreateRequestDto;
import bssm.ber.web.dto.posts.posts.response.ManagerPostsResponseDto;

import java.util.List;

public interface ManagerPostsService {
    Long create(ManagerPostsCreateRequestDto request);

    List<ManagerPostsResponseDto> findByTitle(String title);

    List<ManagerPostsResponseDto> all();

    ManagerPostsResponseDto findById(Long id);

    Long update(Long id, ManagerPostsCreateRequestDto request);

    Long delete(Long id);
}
