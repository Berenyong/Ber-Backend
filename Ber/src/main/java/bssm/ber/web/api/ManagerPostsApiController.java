package bssm.ber.web.api;

import bssm.ber.service.ManagerPostsService;
import bssm.ber.web.dto.posts.ManagerPostsCreateRequestDto;
import bssm.ber.web.dto.posts.ManagerPostsCreateResponseDto;
import bssm.ber.web.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ber/api/posts/manager")
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
    public ManagerPostsCreateResponseDto findById(@PathVariable Long id) {
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