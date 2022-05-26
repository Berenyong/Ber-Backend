package bssm.localhost.service;

import bssm.localhost.web.dto.FreePostsCreateRequestDto;
import bssm.localhost.web.dto.FreePostsCreateResponseDto;

import java.util.List;

public interface FreePostsService {

    Long create(FreePostsCreateRequestDto request);
    List<FreePostsCreateResponseDto> findByTitle(String title);
    FreePostsCreateResponseDto detail(Long id);
    void update(Long id, FreePostsCreateRequestDto request);
    void delete(Long id);
}
