package bssm.ber.web.api.posts.comment;

import bssm.ber.service.posts.comment.MajorPostsCommentService;
import bssm.ber.service.posts.comment.ManagerPostsCommentService;
import bssm.ber.web.dto.posts.comment.request.MajorPostsCommentRequestDto;
import bssm.ber.web.dto.posts.comment.request.ManagerPostsCommentRequestDto;
import bssm.ber.web.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/ber/api/posts/manager/comment")
@RestController
public class ManagerPostsCommentApiController {

    private final ManagerPostsCommentService managerPostsCommentService;

    @PostMapping("/{id}/save")
    public Long save(@PathVariable Long id,
                     @RequestBody ManagerPostsCommentRequestDto requestDto){
        return managerPostsCommentService.saveComment(id, requestDto);
    }

    @GetMapping("/findAll")
    public Result findAll(){
        return new Result(managerPostsCommentService.findAllDesc());
    }

    @PutMapping("/update/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody ManagerPostsCommentRequestDto requestDto) {
        return managerPostsCommentService.updateComment(id, requestDto);
    }

    @DeleteMapping("/delete/{id}")
    public Long delete(@PathVariable Long id) {
        return managerPostsCommentService.deleteComment(id);
    }
}
