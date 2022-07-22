package bssm.ber.service.posts.posts.impl;

import bssm.ber.domain.posts.posts.repository.FreePostsRepository;
import bssm.ber.domain.posts.posts.FreePosts;
import bssm.ber.domain.users.Users;
import bssm.ber.domain.users.UsersRepository;
import bssm.ber.global.config.SecurityUtil;
import bssm.ber.global.exception.CustomException;
import bssm.ber.global.exception.ErrorCode;
import bssm.ber.service.posts.posts.FreePostsService;
import bssm.ber.web.dto.posts.posts.request.FreePostsCreateRequestDto;
import bssm.ber.web.dto.posts.posts.response.FreePostsResponseDto;
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
    private final UsersRepository usersRepository;

    @Transactional
    @Override
    public Long create(FreePostsCreateRequestDto requestDto){
        Users users = usersRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_LOGIN));

        FreePosts freePosts = requestDto.toEntity();
        freePosts.confirmWriter(users);

        return freePostsRepository.save(freePosts).getId();
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
                .orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));

        return FreePostsResponseDto.builder()
                .freePosts(freePosts)
                .build();
    }

    @Transactional
    @Override
    public Long update(Long id, FreePostsCreateRequestDto request) {
        FreePosts freePosts = freePostsRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));

        freePosts.update(request.getTitle(), request.getContent());
        return freePosts.getId();
    }

    @Transactional
    @Override
    public Long delete(Long id) {
        FreePosts freePosts = freePostsRepository.findById(id).get();
        freePostsRepository.deleteById(id);

        return freePosts.getId();
    }
}