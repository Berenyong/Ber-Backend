package bssm.ber.service.posts.posts.impl;

import bssm.ber.domain.posts.comment.FreePostsComment;
import bssm.ber.domain.posts.posts.repository.FreePostsRepository;
import bssm.ber.domain.posts.posts.FreePosts;
import bssm.ber.domain.users.UsersRepository;
import bssm.ber.security.SecurityUtil;
import bssm.ber.service.posts.posts.FreePostsService;
import bssm.ber.web.dto.posts.comment.request.FreePostsCommentRequestDto;
import bssm.ber.web.dto.posts.posts.request.FreePostsCreateRequestDto;
import bssm.ber.web.dto.posts.posts.response.FreePostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final UsersRepository usersRepository;

    @Transactional
    @Override
    public Long create(FreePostsCreateRequestDto requestDto){
        return freePostsRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    public List<FreePostsResponseDto> findByTitle(String title){
        return freePostsRepository.findByTitle(title).stream()
                .map(FreePostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<FreePostsResponseDto> all() {
        return freePostsRepository.findAll().stream()
                .map(FreePostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public FreePostsResponseDto detail(Long id) {
        FreePosts freePosts = freePostsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다."));

        return FreePostsResponseDto.builder()
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