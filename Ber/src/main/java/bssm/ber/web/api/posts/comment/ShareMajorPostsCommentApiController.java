package bssm.ber.web.api.posts.comment;

import bssm.ber.service.posts.comment.ManagerPostsCommentService;
import bssm.ber.service.posts.comment.ShareMajorPostsCommentService;
import bssm.ber.web.dto.posts.comment.request.ManagerPostsCommentRequestDto;
import bssm.ber.web.dto.posts.comment.request.ShareMajorPostsCommentRequestDto;
import bssm.ber.web.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/ber/api/posts/share/major/comment")
@RestController
public class ShareMajorPostsCommentApiController {

    private final ShareMajorPostsCommentService shareMajorPostsCommentService;

    // id == freePosts_id
    @PostMapping("/{id}/create")
    public Long save(@PathVariable Long id,
                     @RequestBody ShareMajorPostsCommentRequestDto requestDto){
        return shareMajorPostsCommentService.saveComment(id, requestDto);
    }

    @GetMapping("/{id}/findAll")
    public Result findAll(@PathVariable Long id){
        return new Result(shareMajorPostsCommentService.findAllDesc(id));
    }

    @PutMapping("/update/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody ShareMajorPostsCommentRequestDto requestDto) {
        return shareMajorPostsCommentService.updateComment(id, requestDto);
    }

    @DeleteMapping("/delete/{id}")
    public Long delete(@PathVariable Long id) {
        return shareMajorPostsCommentService.deleteComment(id);
    }
}
