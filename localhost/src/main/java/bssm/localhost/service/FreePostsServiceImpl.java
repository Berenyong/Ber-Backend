package bssm.localhost.service;

import bssm.localhost.domain.FreePostsRepository;
import bssm.localhost.domain.entity.FreePosts;
import bssm.localhost.web.dto.FreePostsCreateRequestDto;
import bssm.localhost.web.dto.FreePostsCreateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FreePostsServiceImpl implements FreePostsService{

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
    public FreePostsCreateResponseDto detail(Long id) {
        FreePosts freePosts = NoSuchElementPosts(id);

        return FreePostsCreateResponseDto.builder()
                .freePosts(freePosts)
                .build();
    }

    @Transactional
    @Override
    public void update(Long id, FreePostsCreateRequestDto request) {
        FreePosts freePosts = NoSuchElementPosts(id);
        freePosts.update(request.getTitle(), request.getContent());
    }


    @Transactional
    @Override
    public void delete(Long id) {
        freePostsRepository.deleteById(id);
    }


//    게시글 있는지 여부
    public FreePosts NoSuchElementPosts(Long id) {
        FreePosts freePosts = freePostsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시판입니다."));

        return freePosts;
    }


}