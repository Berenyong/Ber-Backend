package bssm.ber.web.api.posts.comment;

import bssm.ber.service.posts.comment.FreePostsCommentService;
import bssm.ber.service.posts.comment.MajorPostsCommentService;
import bssm.ber.web.dto.posts.comment.request.FreePostsCommentRequestDto;
import bssm.ber.web.dto.posts.comment.request.MajorPostsCommentRequestDto;
import bssm.ber.web.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/ber/api/posts/major/comment")
@RestController
public class MajorPostsCommentApiController {

    private final MajorPostsCommentService majorPostsCommentService;

    // id == freePosts_id
    @PostMapping("/{id}/create")
    public Long save(@PathVariable Long id,
                     @RequestBody MajorPostsCommentRequestDto requestDto){
        return majorPostsCommentService.saveComment(id, requestDto);
    }

    @GetMapping("/findAll")
    public Result findAll(){
        return new Result(majorPostsCommentService.findAllDesc());
    }

    @PutMapping("/update/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody MajorPostsCommentRequestDto requestDto) {
        return majorPostsCommentService.updateComment(id, requestDto);
    }

    @DeleteMapping("/delete/{id}")
    public Long delete(@PathVariable Long id) {
        return majorPostsCommentService.deleteComment(id);
    }
}
