package bssm.ber.web.api.users;

import bssm.ber.service.users.UsersService;
import bssm.ber.web.dto.users.UsersJoinRequestDto;
import bssm.ber.web.dto.users.UsersResponseDto;
import bssm.ber.web.dto.users.UsersUpdateRequestDto;
import bssm.ber.web.dto.users.UsersUpdateResponseDto;
import bssm.ber.web.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UsersApiController {

    private final UsersService usersService;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public Long joinUser(@RequestBody UsersJoinRequestDto usersJoinRequestDto) throws Exception {
        return usersService.join(usersJoinRequestDto);
    }

    @GetMapping("/find/all/{nickname}")
    @ResponseStatus(HttpStatus.OK)
    public Result findUsers(@PathVariable String nickname){
        return new Result(usersService.findUsers(nickname));
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsersResponseDto findUser(@PathVariable Long id){
        return usersService.findUser(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteUser(@PathVariable Long id){
        return usersService.delete(id);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody Map<String, String> users) {
        return usersService.login(users);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UsersUpdateResponseDto update(@RequestBody UsersUpdateRequestDto request) throws Exception {
        return usersService.update(request);
    }

}
