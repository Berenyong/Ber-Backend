package bssm.ber.service.impl;

import bssm.ber.domain.entity.posts.ManagerPosts;
import bssm.ber.domain.entity.posts.repository.ManagerPostsRepository;
import bssm.ber.service.ManagerPostsService;
import bssm.ber.web.dto.posts.ManagerPostsCreateRequestDto;
import bssm.ber.web.dto.posts.ManagerPostsCreateResponseDto;
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
    public List<ManagerPostsCreateResponseDto> findByTitle(String title){
        return managerPostsRepository.findByTitle(title).stream()
                .map(ManagerPostsCreateResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ManagerPostsCreateResponseDto> all() {
        return managerPostsRepository.findAll().stream()
                .map(ManagerPostsCreateResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ManagerPostsCreateResponseDto findById(Long id) {
        ManagerPosts managerPosts = managerPostsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다."));

        return ManagerPostsCreateResponseDto.builder()
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
