package bssm.ber.service.posts.comment.impl;

import bssm.ber.domain.posts.posts.FreePosts;
import bssm.ber.domain.posts.comment.FreePostsComment;
import bssm.ber.domain.posts.comment.repository.FreePostsCommentRepository;
import bssm.ber.domain.posts.posts.repository.FreePostsRepository;
import bssm.ber.domain.users.UsersRepository;
import bssm.ber.global.config.SecurityUtil;
import bssm.ber.service.posts.comment.FreePostsCommentService;
import bssm.ber.web.dto.posts.comment.request.FreePostsCommentRequestDto;
import bssm.ber.web.dto.posts.comment.response.FreePostsCommentResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FreePostsCommentServiceImpl implements FreePostsCommentService {

    private final UsersRepository usersRepository;
    private final FreePostsRepository freePostsRepository;
    private final FreePostsCommentRepository freePostsCommentRepository;

    @Transactional
    @Override
    public Long saveComment(Long id, FreePostsCommentRequestDto requestDto) {
        Optional<FreePosts> byId = Optional.of(freePostsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")));
        FreePosts freePosts = byId.get();

        requestDto.setFreePosts(freePosts);

        FreePostsComment freePostsComment = requestDto.toEntity();

        freePostsComment
                .confirmWriter(usersRepository
                        .findByEmail(SecurityUtil.getLoginUserEmail())
                        .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다.")));

        freePostsComment
                .confirmPost(freePostsRepository
                        .findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다.")));

        freePostsCommentRepository.save(freePostsComment);

        log.info(freePostsComment.getId().toString());
        return freePostsComment.getId();
    }

    @Override
    public List<FreePostsCommentResponseDto> findAllDesc(Long id) {
        return freePostsCommentRepository.findAllDesc(id)
                .stream()
                .map(FreePostsCommentResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public String updateComment(Long id, FreePostsCommentRequestDto requestDto) {
        FreePostsComment freePostsComment = freePostsCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        freePostsComment.updateComment(requestDto.getComment());
        return freePostsComment.getComment();
    }

    @Transactional
    @Override
    public Long deleteComment(Long id) {
        Optional<FreePostsComment> comment = Optional.ofNullable(freePostsCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다.")));

        freePostsCommentRepository.deleteById(id);
        return comment.get().getId();
    }
}
