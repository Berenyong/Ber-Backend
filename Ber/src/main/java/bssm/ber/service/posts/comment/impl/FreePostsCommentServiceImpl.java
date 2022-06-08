package bssm.ber.service.posts.comment.impl;

import bssm.ber.domain.entity.posts.posts.FreePosts;
import bssm.ber.domain.entity.posts.comment.FreePostsComment;
import bssm.ber.domain.entity.posts.comment.repository.FreePostsCommentRepository;
import bssm.ber.domain.entity.posts.posts.repository.FreePostsRepository;
import bssm.ber.domain.entity.users.UsersRepository;
import bssm.ber.security.SecurityUtil;
import bssm.ber.service.posts.comment.FreePostsCommentService;
import bssm.ber.web.dto.posts.comment.request.FreePostsCommentRequestDto;
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

        return requestDto.getId();
    }

    @Override
    public List<FreePostsCommentResponseDto> findAllDesc() {
        return freePostsCommentRepository.findAllDesc()
                .stream()
                .map(FreePostsCommentResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Long updateComment(Long id, FreePostsCommentRequestDto requestDto) {
        FreePostsComment freePostsComment = freePostsCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        freePostsComment.updateComment(requestDto.getComment());
        return freePostsComment.getId();
    }

    @Override
    public Long deleteComment(Long id) {
        Optional<FreePostsComment> byId = freePostsCommentRepository.findById(id);
        FreePostsComment freePostsComment = byId.get();

        freePostsCommentRepository.deleteById(id);
        return freePostsComment.getId();
    }
}
