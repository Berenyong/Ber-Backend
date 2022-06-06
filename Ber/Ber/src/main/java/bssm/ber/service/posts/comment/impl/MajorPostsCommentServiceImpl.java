package bssm.ber.service.posts.comment.impl;

import bssm.ber.domain.entity.posts.comment.MajorPostsComment;
import bssm.ber.domain.entity.posts.comment.repository.MajorPostsCommentRepository;
import bssm.ber.domain.entity.posts.posts.MajorPosts;
import bssm.ber.domain.entity.posts.posts.repository.MajorPostsRepository;
import bssm.ber.service.posts.comment.MajorPostsCommentService;
import bssm.ber.web.dto.posts.comment.request.MajorPostsCommentRequestDto;
import bssm.ber.web.dto.posts.comment.response.MajorPostsCommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MajorPostsCommentServiceImpl implements MajorPostsCommentService {

    private final MajorPostsCommentRepository majorPostsCommentRepository;
    private final MajorPostsRepository majorPostsRepository;

    @Transactional
    @Override
    public Long saveComment(Long id, MajorPostsCommentRequestDto requestDto) {
        Optional<MajorPosts> byId = Optional.of(majorPostsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")));
        MajorPosts majorPosts = byId.get();

        requestDto.setMajorPosts(majorPosts);
        MajorPostsComment majorPostsComment = requestDto.toEntity();
        majorPostsCommentRepository.save(majorPostsComment);

        return requestDto.getId();
    }

    @Override
    public List<MajorPostsCommentResponseDto> findAllDesc() {
        return majorPostsCommentRepository.findAllDesc()
                .stream()
                .map(MajorPostsCommentResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Long updateComment(Long id, MajorPostsCommentRequestDto requestDto) {
        MajorPostsComment majorPostsComment = majorPostsCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        majorPostsComment.updateComment(requestDto.getComment());
        return majorPostsComment.getId();
    }

    @Override
    public Long deleteComment(Long id) {
        Optional<MajorPostsComment> byId = majorPostsCommentRepository.findById(id);
        MajorPostsComment majorPostsComment = byId.get();

        majorPostsCommentRepository.deleteById(id);
        return majorPostsComment.getId();
    }


}
