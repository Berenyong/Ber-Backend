package bssm.ber.service;

import bssm.ber.web.dto.posts.MajorPostsCreateRequestDto;
import bssm.ber.web.dto.posts.MajorPostsCreateResponseDto;

import java.util.List;

public interface MajorPostsService {

    Long create(MajorPostsCreateRequestDto request);

    List<MajorPostsCreateResponseDto> findByTitle(String title);

    List<MajorPostsCreateResponseDto> all();

    MajorPostsCreateResponseDto detail(Long id);

    Long update(Long id, MajorPostsCreateRequestDto request);

    Long delete(Long id);
}
