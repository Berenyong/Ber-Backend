package bssm.ber.service.posts.comment.impl;

import bssm.ber.domain.entity.posts.posts.FreePosts;
import bssm.ber.domain.entity.posts.comment.FreePostsComment;
import bssm.ber.domain.entity.posts.comment.repository.FreePostsCommentRepository;
import bssm.ber.domain.entity.posts.posts.repository.FreePostsRepository;
import bssm.ber.service.posts.comment.FreePostsCommentService;
import bssm.ber.web.dto.posts.comment.response.FreePostsCommentRequestDto;
import bssm.ber.web.dto.posts.comment.response.FreePostsCommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FreePostsCommentServiceImpl implements FreePostsCommentService {

    private final FreePostsCommentRepository freePostsCommentRepository;
    private final FreePostsRepository freePostsRepository;

    @Transactional
    @Override
    public Long saveComment(Long id, FreePostsCommentRequestDto requestDto) {
        Optional<FreePosts> byId = Optional.of(freePostsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")));
        FreePosts freePosts = byId.get();

        requestDto.setFreePosts(freePosts);
        FreePostsComment freePostsComment = requestDto.toEntity();
        freePostsCommentRepository.save(freePostsComment);

        return requestDto.getId();
    }

    @Override
    public List<FreePostsCommentResponseDto> findAllDesc() {
        return freePostsCommentRepository.findAllDesc()
                .stream()
                .map(FreePostsCommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
