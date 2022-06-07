package bssm.ber.service.posts.comment;

import bssm.ber.web.dto.posts.comment.request.MajorPostsCommentRequestDto;
import bssm.ber.web.dto.posts.comment.response.MajorPostsCommentResponseDto;

import java.util.List;

public interface MajorPostsCommentService {

    public Long saveComment(Long id, MajorPostsCommentRequestDto requestDto);

    public List<MajorPostsCommentResponseDto> findAllDesc();

    public Long updateComment(Long id, MajorPostsCommentRequestDto requestDto);
    public Long deleteComment(Long id);
}
