package bssm.ber.web.api.posts.comment;

import bssm.ber.service.posts.comment.FreePostsCommentService;
import bssm.ber.web.dto.posts.comment.response.FreePostsCommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/ber/api/posts/free/comment")
@RestController
public class FreePostsCommentApiController {

    private final FreePostsCommentService freePostsCommentService;

    @PostMapping("/{id}/save")
    public Long save(@PathVariable Long id,
                     @RequestBody FreePostsCommentRequestDto requestDto){
        return freePostsCommentService.saveComment(id, requestDto);
    }
}
