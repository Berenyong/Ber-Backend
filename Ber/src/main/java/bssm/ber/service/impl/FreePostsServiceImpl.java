package bssm.ber.service.impl;

import bssm.ber.domain.entity.posts.repository.FreePostsRepository;
import bssm.ber.domain.entity.posts.FreePosts;
import bssm.ber.service.FreePostsService;
import bssm.ber.web.dto.posts.FreePostsCreateRequestDto;
import bssm.ber.web.dto.posts.FreePostsCreateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FreePostsServiceImpl implements FreePostsService {

    private final FreePostsRepository freePostsRepository;

    @Transactional
    @Override
    public Long create(FreePostsCreateRequestDto requestDto){
        return freePostsRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    public List<FreePostsCreateResponseDto> findByTitle(String title){
        return freePostsRepository.findByTitle(title).stream()
                .map(FreePostsCreateResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<FreePostsCreateResponseDto> all() {
        return freePostsRepository.findAll().stream()
                .map(FreePostsCreateResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public FreePostsCreateResponseDto detail(Long id) {
        FreePosts freePosts = freePostsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다."));

        return FreePostsCreateResponseDto.builder()
                .freePosts(freePosts)
                .build();
    }

    @Transactional
    @Override
    public Long update(Long id, FreePostsCreateRequestDto request) {
        FreePosts freePosts = freePostsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다."));

        freePosts.update(request.getTitle(), request.getContent());
        return freePosts.getId();
    }

    @Transactional
    @Override
    public Long delete(Long id) {
        Optional<FreePosts> byId = freePostsRepository.findById(id);
        FreePosts freePosts = byId.get();

        freePostsRepository.deleteById(id);
        return freePosts.getId();
    }
}