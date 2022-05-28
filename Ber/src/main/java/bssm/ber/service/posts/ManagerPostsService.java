package bssm.ber.service.posts;

import bssm.ber.web.dto.posts.ManagerPostsCreateRequestDto;
import bssm.ber.web.dto.posts.ManagerPostsResponseDto;

import java.util.List;

public interface ManagerPostsService {
    Long create(ManagerPostsCreateRequestDto request);

    List<ManagerPostsResponseDto> findByTitle(String title);

    List<ManagerPostsResponseDto> all();

    ManagerPostsResponseDto findById(Long id);

    Long update(Long id, ManagerPostsCreateRequestDto request);

    Long delete(Long id);
}
