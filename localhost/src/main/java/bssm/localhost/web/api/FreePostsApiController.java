package bssm.localhost.web.api;

import bssm.localhost.service.FreePostsService;
import bssm.localhost.web.dto.FreePostsCreateRequestDto;
import bssm.localhost.web.generic.Result;
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
}
