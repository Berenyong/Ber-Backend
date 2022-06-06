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

    @PostMapping("/{id}/save")
    public Long save(@PathVariable Long id,
                     @RequestBody FreePostsCommentRequestDto requestDto){
        return freePostsCommentService.saveComment(id, requestDto);
    }

    @GetMapping("/findAll")
    public Result findAll(){
        return new Result(freePostsCommentService.findAllDesc());
    }

    @PutMapping("/update/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody FreePostsCommentRequestDto requestDto) {
        return freePostsCommentService.updateComment(id, requestDto);
    }

    @DeleteMapping("/delete/{id}")
    public Long delete(@PathVariable Long id) {
        return freePostsCommentService.deleteComment(id);
    }
}
