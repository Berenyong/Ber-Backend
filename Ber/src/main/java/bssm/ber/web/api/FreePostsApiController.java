package bssm.ber.web.api;

import bssm.ber.service.FreePostsService;
import bssm.ber.web.dto.posts.FreePostsCreateRequestDto;
import bssm.ber.web.dto.posts.FreePostsCreateResponseDto;
import bssm.ber.web.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ber/api/posts/free")
@RequiredArgsConstructor
@RestController
public class FreePostsApiController {

    private final FreePostsService freePostsService;

    @PostMapping("/create")
    public Long createPost(@RequestBody FreePostsCreateRequestDto freePostsCreateRequestDto){
        return freePostsService.create(freePostsCreateRequestDto);
    }

    @GetMapping("/find/title/{title}")
    public Result findByTitle(@PathVariable String title){
        return new Result(freePostsService.findByTitle(title));
    }

    @GetMapping("/findAll")
    public Result allPosts() {
        return new Result(freePostsService.all());
    }

    @GetMapping("/find/{id}")
    public FreePostsCreateResponseDto detailPosts(@PathVariable Long id) {
        return freePostsService.detail(id);
    }

    @PutMapping("/update/{id}")
    public Long updatePosts(@PathVariable Long id, @RequestBody FreePostsCreateRequestDto request) {
        return freePostsService.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public Long deletePosts(@PathVariable Long id) {
        return freePostsService.delete(id);
    }
}
