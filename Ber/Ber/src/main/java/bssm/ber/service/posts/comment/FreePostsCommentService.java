package bssm.ber.service.posts.comment;

import bssm.ber.web.dto.posts.comment.request.FreePostsCommentRequestDto;
import bssm.ber.web.dto.posts.comment.response.FreePostsCommentResponseDto;

import java.util.List;

public interface FreePostsCommentService {

    public Long saveComment(Long id, FreePostsCommentRequestDto requestDto);

    public List<FreePostsCommentResponseDto> findAllDesc();

    public Long updateComment(Long id, FreePostsCommentRequestDto requestDto);
    public Long deleteComment(Long id);
}