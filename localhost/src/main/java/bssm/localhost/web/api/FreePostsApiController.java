package bssm.localhost.web.api;

import bssm.localhost.service.FreePostsServiceImpl;
import bssm.localhost.web.dto.FreePostsCreateRequestDto;
import bssm.localhost.web.dto.FreePostsCreateResponseDto;
import bssm.localhost.web.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ber/api/posts/free")
@RequiredArgsConstructor
@RestController
public class FreePostsApiController {

    private final FreePostsServiceImpl freePostsService;

    @PostMapping("/create")
    public Long createPost(@RequestBody FreePostsCreateRequestDto freePostsCreateRequestDto){
        return freePostsService.create(freePostsCreateRequestDto);
    }

    @GetMapping("/find/title/{title}")
    public Result findByTitle(@PathVariable String title){
        return new Result(freePostsService.findByTitle(title));
    }

    @GetMapping("/{id}")
    public FreePostsCreateResponseDto detailPosts(@PathVariable Long id) {
        return freePostsService.detail(id);
    }

    @PutMapping("/{id}/edit")
    public void updatePosts(@PathVariable Long id,
                            @RequestBody FreePostsCreateRequestDto request) {
        freePostsService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletePosts(Long id) {
        freePostsService.delete(id);
    }

}
