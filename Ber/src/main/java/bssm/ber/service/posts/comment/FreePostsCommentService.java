package bssm.ber.service.posts.comment;

import bssm.ber.web.dto.posts.comment.response.FreePostsCommentRequestDto;
import bssm.ber.web.dto.posts.comment.response.FreePostsCommentResponseDto;

import java.util.List;

public interface FreePostsCommentService {

    public Long saveComment(Long id, FreePostsCommentRequestDto requestDto);

    public List<FreePostsCommentResponseDto> findAllDesc();
}
