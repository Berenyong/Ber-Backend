package bssm.localhost.service;

import bssm.localhost.domain.FreePostsRepository;
import bssm.localhost.domain.entity.FreePosts;
import bssm.localhost.web.dto.FreePostsCreateRequestDto;
import bssm.localhost.web.dto.FreePostsCreateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FreePostsService {

    private final FreePostsRepository freePostsRepository;

    @Transactional
    public Long create(FreePostsCreateRequestDto requestDto){
        return freePostsRepository.save(requestDto.toEntity()).getId();
    }

    public List<FreePostsCreateResponseDto> findByTitle(String title){
        return freePostsRepository.findByTitle(title).stream()
                .map(FreePostsCreateResponseDto::new)
                .collect(Collectors.toList());
        }
}