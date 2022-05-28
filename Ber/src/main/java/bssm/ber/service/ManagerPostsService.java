package bssm.ber.service;

import bssm.ber.web.dto.posts.ManagerPostsCreateRequestDto;
import bssm.ber.web.dto.posts.ManagerPostsCreateResponseDto;

import java.util.List;

public interface ManagerPostsService {
    Long create(ManagerPostsCreateRequestDto request);

    List<ManagerPostsCreateResponseDto> findByTitle(String title);

    List<ManagerPostsCreateResponseDto> all();

    ManagerPostsCreateResponseDto findById(Long id);

    Long update(Long id, ManagerPostsCreateRequestDto request);

    Long delete(Long id);
}
