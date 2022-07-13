package bssm.ber.web.api.posts.comment;

import bssm.ber.service.posts.comment.FreePostsCommentService;
import bssm.ber.web.dto.posts.comment.request.FreePostsCommentRequestDto;
import bssm.ber.web.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/comment/free")
@RestController
public class FreePostsCommentApiController {

    private final FreePostsCommentService freePostsCommentService;

    // id == freePosts_id
    @PostMapping("/create/{id}")
    public Long save(@PathVariable Long id,
                     @RequestBody FreePostsCommentRequestDto requestDto){
        return freePostsCommentService.saveComment(id, requestDto);
    }

    // 현재 접속한 게시판의 댓글을 모두 조회함
    @GetMapping("/findAll/{id}")
    public Result findAll(@PathVariable Long id){
        return new Result(freePostsCommentService.findAllDesc(id));
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id,
                       @RequestBody FreePostsCommentRequestDto requestDto) {
        return freePostsCommentService.updateComment(id, requestDto);
    }

    // id == comment_id
    @DeleteMapping("/delete/{id}")
    public Long delete(@PathVariable Long id) {
        return freePostsCommentService.deleteComment(id);
    }
}
