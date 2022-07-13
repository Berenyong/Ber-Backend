package bssm.ber.service.posts.comment.impl;

import bssm.ber.domain.posts.comment.ManagerPostsComment;
import bssm.ber.domain.posts.comment.repository.ManagerPostsCommentRepository;
import bssm.ber.domain.posts.posts.ManagerPosts;
import bssm.ber.domain.posts.posts.repository.ManagerPostsRepository;
import bssm.ber.domain.users.UsersRepository;
import bssm.ber.global.config.SecurityUtil;
import bssm.ber.service.posts.comment.ManagerPostsCommentService;
import bssm.ber.web.dto.posts.comment.request.ManagerPostsCommentRequestDto;
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
    private final UsersRepository usersRepository;

    @Transactional
    @Override
    public Long saveComment(Long id, ManagerPostsCommentRequestDto requestDto) {
        Optional<ManagerPosts> byId = Optional.of(managerPostsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")));
        ManagerPosts managerPosts = byId.get();

        requestDto.setManagerPosts(managerPosts);

        ManagerPostsComment managerPostsComment = requestDto.toEntity();

        managerPostsComment
                .confirmWriter(usersRepository
                        .findByEmail(SecurityUtil.getLoginUserEmail())
                        .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다.")));

        managerPostsComment
                .confirmPost(managerPostsRepository
                        .findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다.")));

        managerPostsCommentRepository.save(managerPostsComment);

        return requestDto.getId();
    }

    @Override
    public List<ManagerPostsCommentResponseDto> findAllDesc(Long id) {
        return managerPostsCommentRepository.findAllDesc(id)
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
