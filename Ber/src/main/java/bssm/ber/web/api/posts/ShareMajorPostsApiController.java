package bssm.ber.web.api.posts;

import bssm.ber.service.posts.ShareMajorPostsService;
import bssm.ber.web.dto.posts.ShareMajorPostsCreateRequestDto;
import bssm.ber.web.dto.posts.ShareMajorPostsResponseDto;
import bssm.ber.web.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ber/api/posts/share/major")
@RequiredArgsConstructor
@RestController
public class ShareMajorPostsApiController {

    private final ShareMajorPostsService shareMajorPostsService;

    @PostMapping("/create")
    public Long createPost(@RequestBody ShareMajorPostsCreateRequestDto shareMajorPostsCreateRequestDto){
        return shareMajorPostsService.create(shareMajorPostsCreateRequestDto);
    }

    @GetMapping("/find/title/{title}")
    public Result findByTitle(@PathVariable String title){
        return new Result(shareMajorPostsService.findByTitle(title));
    }

    @GetMapping("/findAll")
    public Result allPosts() {
        return new Result(shareMajorPostsService.all());
    }

    @GetMapping("/find/{id}")
    public ShareMajorPostsResponseDto detailPosts(@PathVariable Long id) {
        return shareMajorPostsService.detail(id);
    }

    @PutMapping("/update/{id}")
    public Long updatePosts(@PathVariable Long id, @RequestBody ShareMajorPostsCreateRequestDto request) {
        return shareMajorPostsService.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public Long deletePosts(@PathVariable Long id) {
        return shareMajorPostsService.delete(id);
    }
}


