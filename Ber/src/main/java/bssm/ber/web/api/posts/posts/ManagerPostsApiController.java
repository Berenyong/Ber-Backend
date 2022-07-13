package bssm.ber.web.api.posts.posts;

import bssm.ber.service.posts.posts.ManagerPostsService;
import bssm.ber.web.dto.posts.posts.request.ManagerPostsCreateRequestDto;
import bssm.ber.web.dto.posts.posts.response.ManagerPostsResponseDto;
import bssm.ber.web.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/posts/manager")
@RequiredArgsConstructor
@RestController
public class ManagerPostsApiController {

    private final ManagerPostsService managerPostsService;

    @PostMapping("/create")
    public Long createPost(@RequestBody ManagerPostsCreateRequestDto managerPostsCreateRequestDto){
        return managerPostsService.create(managerPostsCreateRequestDto);
    }

    @GetMapping("/find/title/{title}")
    public Result findByTitle(@PathVariable String title){
        return new Result(managerPostsService.findByTitle(title));
    }

    @GetMapping("/findAll")
    public Result allPosts() {
        return new Result(managerPostsService.all());
    }

    @GetMapping("/find/{id}")
    public ManagerPostsResponseDto findById(@PathVariable Long id) {
        return managerPostsService.findById(id);
    }

    @PutMapping("/update/{id}")
    public Long updatePosts(@PathVariable Long id, @RequestBody ManagerPostsCreateRequestDto request) {
        return managerPostsService.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public Long deletePosts(@PathVariable Long id) {
        return managerPostsService.delete(id);
    }
}