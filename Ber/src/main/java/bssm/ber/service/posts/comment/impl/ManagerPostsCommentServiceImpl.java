package bssm.ber.service.posts.comment.impl;

import bssm.ber.domain.entity.posts.comment.MajorPostsComment;
import bssm.ber.domain.entity.posts.comment.ManagerPostsComment;
import bssm.ber.domain.entity.posts.comment.repository.MajorPostsCommentRepository;
import bssm.ber.domain.entity.posts.comment.repository.ManagerPostsCommentRepository;
import bssm.ber.domain.entity.posts.posts.MajorPosts;
import bssm.ber.domain.entity.posts.posts.ManagerPosts;
import bssm.ber.domain.entity.posts.posts.repository.MajorPostsRepository;
import bssm.ber.domain.entity.posts.posts.repository.ManagerPostsRepository;
import bssm.ber.service.posts.comment.MajorPostsCommentService;
import bssm.ber.service.posts.comment.ManagerPostsCommentService;
import bssm.ber.web.dto.posts.comment.request.MajorPostsCommentRequestDto;
import bssm.ber.web.dto.posts.comment.request.ManagerPostsCommentRequestDto;
import bssm.ber.web.dto.posts.comment.response.MajorPostsCommentResponseDto;
import bssm.ber.web.dto.posts.comment.response.ManagerPostsCommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerPostsCommentServiceImpl implements ManagerPostsCommentService {

    private final ManagerPostsCommentRepository managerPostsCommentRepository;
    private final ManagerPostsRepository managerPostsRepository;

    @Transactional
    @Override
    public Long saveComment(Long id, ManagerPostsCommentRequestDto requestDto) {
        Optional<ManagerPosts> byId = Optional.of(managerPostsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")));
        ManagerPosts managerPosts = byId.get();

        requestDto.setManagerPosts(managerPosts);
        ManagerPostsComment managerPostsComment = requestDto.toEntity();
        managerPostsCommentRepository.save(managerPostsComment);

        return requestDto.getId();
    }

    @Override
    public List<ManagerPostsCommentResponseDto> findAllDesc() {
        return managerPostsCommentRepository.findAllDesc()
                .stream()
                .map(ManagerPostsCommentResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Long updateComment(Long id, ManagerPostsCommentRequestDto requestDto) {
        ManagerPostsComment managerPostsComment = managerPostsCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        managerPostsComment.updateComment(requestDto.getComment());
        return managerPostsComment.getId();
    }

    @Override
    public Long deleteComment(Long id) {
        Optional<ManagerPostsComment> byId = managerPostsCommentRepository.findById(id);
        ManagerPostsComment managerPostsComment = byId.get();

        managerPostsCommentRepository.deleteById(id);
        return managerPostsComment.getId();
    }


}
