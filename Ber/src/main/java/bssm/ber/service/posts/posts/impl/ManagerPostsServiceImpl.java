package bssm.ber.service.posts.posts.impl;

import bssm.ber.domain.entity.posts.posts.ManagerPosts;
import bssm.ber.domain.entity.posts.posts.repository.ManagerPostsRepository;
import bssm.ber.service.posts.posts.ManagerPostsService;
import bssm.ber.web.dto.posts.posts.request.ManagerPostsCreateRequestDto;
import bssm.ber.web.dto.posts.posts.response.ManagerPostsResponseDto;
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
public class ManagerPostsServiceImpl implements ManagerPostsService {

    private final ManagerPostsRepository managerPostsRepository;

    @Transactional
    @Override
    public Long create(ManagerPostsCreateRequestDto requestDto){
        return managerPostsRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    public List<ManagerPostsResponseDto> findByTitle(String title){
        return managerPostsRepository.findByTitle(title).stream()
                .map(ManagerPostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ManagerPostsResponseDto> all() {
        return managerPostsRepository.findAll().stream()
                .map(ManagerPostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ManagerPostsResponseDto findById(Long id) {
        ManagerPosts managerPosts = managerPostsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다."));

        return ManagerPostsResponseDto.builder()
                .managerPosts(managerPosts)
                .build();
    }

    @Transactional
    @Override
    public Long update(Long id, ManagerPostsCreateRequestDto request) {
        ManagerPosts managerPosts = managerPostsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다."));

        managerPosts.update(request.getTitle(), request.getContent());
        return managerPosts.getId();
    }

    @Transactional
    @Override
    public Long delete(Long id) {
        Optional<ManagerPosts> byId = managerPostsRepository.findById(id);
        ManagerPosts managerPosts = byId.get();

        managerPostsRepository.deleteById(id);
        return managerPosts.getId();
    }
}
