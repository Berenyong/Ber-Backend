package bssm.ber.service.posts.posts.impl;

import bssm.ber.domain.posts.posts.ShareMajorPosts;
import bssm.ber.domain.posts.posts.repository.ShareMajorPostsRepository;
import bssm.ber.service.posts.posts.ShareMajorPostsService;
import bssm.ber.web.dto.posts.posts.request.ShareMajorPostsCreateRequestDto;
import bssm.ber.web.dto.posts.posts.response.ShareMajorPostsResponseDto;
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
public class ShareMajorPostsServiceImpl implements ShareMajorPostsService {

    private final ShareMajorPostsRepository shareMajorPostsRepository;

    @Transactional
    @Override
    public Long create(ShareMajorPostsCreateRequestDto requestDto){
        return shareMajorPostsRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    public List<ShareMajorPostsResponseDto> findByTitle(String title){
        return shareMajorPostsRepository.findByTitle(title).stream()
                .map(ShareMajorPostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShareMajorPostsResponseDto> all() {
        return shareMajorPostsRepository.findAll().stream()
                .map(ShareMajorPostsResponseDto::new)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<ShareMajorPostsResponseDto> all() {
//        return shareMajorPostsRepository.findAll().stream()
//                .filter(shareMajorPosts -> shareMajorPosts.getTitle().equals("title")
//                        && shareMajorPosts.getContent().equals("content"))
//                .map(ShareMajorPostsResponseDto::new)
//                .collect(Collectors.toList());
//    }

    @Override
    public ShareMajorPostsResponseDto detail(Long id) {
        ShareMajorPosts shareMajorPosts = shareMajorPostsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다."));

        return ShareMajorPostsResponseDto.builder()
                .shareMajorPosts(shareMajorPosts)
                .build();
    }

    @Transactional
    @Override
    public Long update(Long id, ShareMajorPostsCreateRequestDto request) {
        ShareMajorPosts shareMajorPosts = shareMajorPostsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다."));

        shareMajorPosts.update(request.getTitle(), request.getContent());
        return shareMajorPosts.getId();
    }

    @Transactional
    @Override
    public Long delete(Long id) {
        Optional<ShareMajorPosts> byId = shareMajorPostsRepository.findById(id);
        ShareMajorPosts shareMajorPosts = byId.get();

        shareMajorPostsRepository.deleteById(id);
        return shareMajorPosts.getId();
    }
}
