package bssm.ber.service.posts.comment;

import bssm.ber.web.dto.posts.comment.request.ManagerPostsCommentRequestDto;
import bssm.ber.web.dto.posts.comment.response.ManagerPostsCommentResponseDto;

import java.util.List;

public interface ManagerPostsCommentService {

    public Long saveComment(Long id, ManagerPostsCommentRequestDto requestDto);

    public List<ManagerPostsCommentResponseDto> findAllDesc(Long id);

    public Long updateComment(Long id, ManagerPostsCommentRequestDto requestDto);
    public Long deleteComment(Long id);
}
