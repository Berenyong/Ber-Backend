package bssm.ber.web.api.posts;

import bssm.ber.service.posts.MajorPostsService;
import bssm.ber.web.dto.posts.MajorPostsCreateRequestDto;
import bssm.ber.web.dto.posts.MajorPostsResponseDto;
import bssm.ber.web.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ber/api/posts/major")
@RequiredArgsConstructor
@RestController
public class MajorPostsApiController {

    private final MajorPostsService majorPostsService;

    @PostMapping("/create")
    public Long createPost(@RequestBody MajorPostsCreateRequestDto majorPostsCreateRequestDto){
        return majorPostsService.create(majorPostsCreateRequestDto);
    }

    @GetMapping("/find/title/{title}")
    public Result findByTitle(@PathVariable String title){
        return new Result(majorPostsService.findByTitle(title));
    }

    @GetMapping("/findAll")
    public Result allPosts() {
        return new Result(majorPostsService.all());
    }

    @GetMapping("/find/{id}")
    public MajorPostsResponseDto detailPosts(@PathVariable Long id) {
        return majorPostsService.detail(id);
    }

    @PutMapping("/update/{id}")
    public Long updatePosts(@PathVariable Long id, @RequestBody MajorPostsCreateRequestDto request) {
        return majorPostsService.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public Long deletePosts(@PathVariable Long id) {
        return majorPostsService.delete(id);
    }
}

