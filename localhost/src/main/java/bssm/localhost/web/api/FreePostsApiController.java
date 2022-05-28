package bssm.localhost.web.api;

import bssm.localhost.domain.entity.FreePosts;
import bssm.localhost.service.FreePostsService;
import bssm.localhost.web.dto.FreePostsCreateRequestDto;
import bssm.localhost.web.dto.FreePostsCreateResponseDto;
import bssm.localhost.web.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        List<FreePostsCreateResponseDto> byTitle = freePostsService.findByTitle(title);
        List<FreePostsCreateResponseDto> collect = byTitle.stream()
                .map(p -> new FreePostsCreateResponseDto(p.getId(), p.getTitle(), p.getContent()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @GetMapping("/find")
    public Result allPosts() {
        List<FreePostsCreateResponseDto> all = freePostsService.all();
        List<FreePostsCreateResponseDto> collect = all.stream()
                .map(p -> new FreePostsCreateResponseDto(p.getId(), p.getTitle(), p.getContent()))
                .collect(Collectors.toList());

        return new Result(collect);
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
