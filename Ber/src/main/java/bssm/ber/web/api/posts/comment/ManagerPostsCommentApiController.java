package bssm.ber.web.api.posts.comment;

import bssm.ber.service.posts.comment.ManagerPostsCommentService;
import bssm.ber.web.dto.posts.comment.request.ManagerPostsCommentRequestDto;
import bssm.ber.web.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/manager/comment")
@RestController
public class ManagerPostsCommentApiController {

    private final ManagerPostsCommentService managerPostsCommentService;

    // id == freePosts_id
    @PostMapping("/{id}/create")
    public Long save(@PathVariable Long id,
                     @RequestBody ManagerPostsCommentRequestDto requestDto){
        return managerPostsCommentService.saveComment(id, requestDto);
    }

    @GetMapping("/{id}/findAll")
    public Result findAll(@PathVariable Long id){
        return new Result(managerPostsCommentService.findAllDesc(id));
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
