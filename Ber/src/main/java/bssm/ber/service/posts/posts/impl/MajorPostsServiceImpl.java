package bssm.ber.service.posts.posts.impl;

import bssm.ber.domain.posts.posts.MajorPosts;
import bssm.ber.domain.posts.posts.repository.MajorPostsRepository;
import bssm.ber.service.posts.posts.MajorPostsService;
import bssm.ber.web.dto.posts.posts.request.MajorPostsCreateRequestDto;
import bssm.ber.web.dto.posts.posts.response.MajorPostsResponseDto;
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
public class MajorPostsServiceImpl implements MajorPostsService {

    private final MajorPostsRepository majorPostsRepository;

    @Transactional
    @Override
    public Long create(MajorPostsCreateRequestDto requestDto){
        return majorPostsRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    public List<MajorPostsResponseDto> findByTitle(String title){
        return majorPostsRepository.findByTitle(title).stream()
                .map(MajorPostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<MajorPostsResponseDto> all() {
        return majorPostsRepository.findAll().stream()
                .map(MajorPostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public MajorPostsResponseDto detail(Long id) {
        MajorPosts majorPosts = majorPostsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다."));

        return MajorPostsResponseDto.builder()
                .majorPosts(majorPosts)
                .build();
    }

    @Transactional
    @Override
    public Long update(Long id, MajorPostsCreateRequestDto request) {
        MajorPosts majorPosts = majorPostsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다."));

        majorPosts.update(request.getTitle(), request.getContent());
        return majorPosts.getId();
    }

    @Transactional
    @Override
    public Long delete(Long id) {
        Optional<MajorPosts> byId = majorPostsRepository.findById(id);
        MajorPosts majorPosts = byId.get();

        majorPostsRepository.deleteById(id);
        return majorPosts.getId();
    }
}
