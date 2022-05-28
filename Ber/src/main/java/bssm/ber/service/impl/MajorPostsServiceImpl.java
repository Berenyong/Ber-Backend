package bssm.ber.service.impl;

import bssm.ber.domain.entity.posts.MajorPosts;
import bssm.ber.domain.entity.posts.repository.MajorPostsRepository;
import bssm.ber.service.MajorPostsService;
import bssm.ber.web.dto.posts.MajorPostsCreateRequestDto;
import bssm.ber.web.dto.posts.MajorPostsCreateResponseDto;
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
    public List<MajorPostsCreateResponseDto> findByTitle(String title){
        return majorPostsRepository.findByTitle(title).stream()
                .map(MajorPostsCreateResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<MajorPostsCreateResponseDto> all() {
        return majorPostsRepository.findAll().stream()
                .map(MajorPostsCreateResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public MajorPostsCreateResponseDto detail(Long id) {
        MajorPosts majorPosts = majorPostsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다."));

        return MajorPostsCreateResponseDto.builder()
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
