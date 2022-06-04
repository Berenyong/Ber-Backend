package bssm.ber.service.posts.comment;

import bssm.ber.web.dto.posts.comment.response.FreePostsCommentRequestDto;

public interface FreePostsCommentService {

    public Long saveComment(Long id, FreePostsCommentRequestDto requestDto);

}
