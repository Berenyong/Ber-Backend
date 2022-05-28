package bssm.ber.service;

import bssm.ber.web.dto.posts.FreePostsCreateRequestDto;
import bssm.ber.web.dto.posts.FreePostsCreateResponseDto;

import java.util.List;

public interface FreePostsService {

    Long create(FreePostsCreateRequestDto request);

    List<FreePostsCreateResponseDto> findByTitle(String title);

    List<FreePostsCreateResponseDto> all();

    FreePostsCreateResponseDto detail(Long id);

    Long update(Long id, FreePostsCreateRequestDto request);

    Long delete(Long id);
}
