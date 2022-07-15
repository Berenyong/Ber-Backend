package bssm.ber.web.api.posts.posts;

import bssm.ber.service.posts.posts.FreePostsService;
import bssm.ber.web.dto.posts.posts.request.FreePostsCreateRequestDto;
import bssm.ber.web.dto.posts.posts.response.FreePostsResponseDto;
import bssm.ber.web.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/posts/free")
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
    public FreePostsResponseDto detailPosts(@PathVariable Long id) {
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
