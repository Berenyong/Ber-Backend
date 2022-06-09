package bssm.ber.web.api.posts.comment;

import bssm.ber.service.posts.comment.FreePostsCommentService;
import bssm.ber.web.dto.posts.comment.request.FreePostsCommentRequestDto;
import bssm.ber.web.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/ber/api/posts/free/comment")
@RestController
public class FreePostsCommentApiController {

    private final FreePostsCommentService freePostsCommentService;

    // id == freePosts_id
    @PostMapping("/{id}/create")
    public Long save(@PathVariable Long id,
                     @RequestBody FreePostsCommentRequestDto requestDto){
        return freePostsCommentService.saveComment(id, requestDto);
    }

    // 현재 접속한 게시판의 댓글을 모두 조회함
    @GetMapping("/{id}/findAll")
    public Result findAll(@PathVariable Long id){
        return new Result(freePostsCommentService.findAllDesc(id));
    }

    @PutMapping("/{id}/update")
    public Long update(@PathVariable Long id,
                       @RequestBody FreePostsCommentRequestDto requestDto) {
        return freePostsCommentService.updateComment(id, requestDto);
    }

    @DeleteMapping("/{id}/delete")
    public Long delete(@PathVariable Long id) {
        return freePostsCommentService.deleteComment(id);
    }
}
