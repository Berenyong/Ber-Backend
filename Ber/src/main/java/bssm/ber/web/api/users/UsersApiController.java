package bssm.ber.web.api.users;

import bssm.ber.domain.entity.users.Users;
import bssm.ber.service.users.UsersService;
import bssm.ber.web.dto.users.UsersJoinRequestDto;
import bssm.ber.web.dto.users.UsersResponseDto;
import bssm.ber.web.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ber/api/users")
public class UsersApiController {

    private final UsersService usersService;

    @PostMapping("/join")
    public Long joinUser(@RequestBody @Valid UsersJoinRequestDto usersJoinRequestDto){
        return usersService.join(usersJoinRequestDto);
    }

    @GetMapping("/findAll/{nickname}")
    public Result findUsers(@PathVariable String nickname){
        return new Result(usersService.findUsers(nickname));
    }

    @GetMapping("/find/{id}")
    public UsersResponseDto findUser(@PathVariable Long id){
        return usersService.findUser(id);
    }

    @DeleteMapping("/delete/{id}")
    public Long deleteUser(@PathVariable Long id){
        return usersService.delete(id);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> users) {
        return usersService.login(users);
    }
}
