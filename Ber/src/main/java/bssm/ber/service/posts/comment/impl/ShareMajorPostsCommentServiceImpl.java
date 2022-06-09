package bssm.ber.service.posts.comment.impl;

import bssm.ber.domain.posts.comment.ShareMajorPostsComment;
import bssm.ber.domain.posts.comment.repository.ShareMajorPostsCommentRepository;
import bssm.ber.domain.posts.posts.ShareMajorPosts;
import bssm.ber.domain.posts.posts.repository.ShareMajorPostsRepository;
import bssm.ber.domain.users.UsersRepository;
import bssm.ber.security.SecurityUtil;
import bssm.ber.service.posts.comment.ShareMajorPostsCommentService;
import bssm.ber.web.dto.posts.comment.request.ShareMajorPostsCommentRequestDto;
import bssm.ber.web.dto.posts.comment.response.ShareMajorPostsCommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShareMajorPostsCommentServiceImpl implements ShareMajorPostsCommentService {

    private final ShareMajorPostsCommentRepository shareMajorPostsCommentRepository;
    private final ShareMajorPostsRepository shareMajorPostsRepository;
    private final UsersRepository usersRepository;

    @Transactional
    @Override
    public Long saveComment(Long id, ShareMajorPostsCommentRequestDto requestDto) {
        Optional<ShareMajorPosts> byId = Optional.of(shareMajorPostsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")));
        ShareMajorPosts shareMajorPosts = byId.get();

        requestDto.setShareMajorPosts(shareMajorPosts);

        ShareMajorPostsComment shareMajorPostsComment = requestDto.toEntity();

        shareMajorPostsComment
                .confirmWriter(usersRepository
                        .findByEmail(SecurityUtil.getLoginUserEmail())
                        .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다.")));

        shareMajorPostsComment
                .confirmPost(shareMajorPostsRepository
                        .findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다.")));

        shareMajorPostsCommentRepository.save(shareMajorPostsComment);

        return requestDto.getId();
    }

    @Override
    public List<ShareMajorPostsCommentResponseDto> findAllDesc() {
        return shareMajorPostsCommentRepository.findAllDesc()
                .stream()
                .map(ShareMajorPostsCommentResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Long updateComment(Long id, ShareMajorPostsCommentRequestDto requestDto) {
        ShareMajorPostsComment shareMajorPostsComment = shareMajorPostsCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        shareMajorPostsComment.updateComment(requestDto.getComment());
        return shareMajorPostsComment.getId();
    }

    @Override
    public Long deleteComment(Long id) {
        Optional<ShareMajorPostsComment> byId = shareMajorPostsCommentRepository.findById(id);
        ShareMajorPostsComment shareMajorPostsComment = byId.get();

        shareMajorPostsCommentRepository.deleteById(id);
        return shareMajorPostsComment.getId();
    }

}

