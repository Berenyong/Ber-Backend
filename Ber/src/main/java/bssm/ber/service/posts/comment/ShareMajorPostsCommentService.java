package bssm.ber.service.posts.comment;

import bssm.ber.web.dto.posts.comment.request.ShareMajorPostsCommentRequestDto;
import bssm.ber.web.dto.posts.comment.response.ShareMajorPostsCommentResponseDto;

import java.util.List;

public interface ShareMajorPostsCommentService {

    public Long saveComment(Long id, ShareMajorPostsCommentRequestDto requestDto);

    public List<ShareMajorPostsCommentResponseDto> findAllDesc(Long id);

    public Long updateComment(Long id, ShareMajorPostsCommentRequestDto requestDto);
    public Long deleteComment(Long id);
}
