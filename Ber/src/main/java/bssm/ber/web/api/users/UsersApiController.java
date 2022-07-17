package bssm.ber.web.api.users;

import bssm.ber.service.email.EmailService;
import bssm.ber.service.email.impl.EmailServiceImpl;
import bssm.ber.service.users.UsersService;
import bssm.ber.web.dto.users.UsersJoinRequestDto;
import bssm.ber.web.dto.users.UsersResponseDto;
import bssm.ber.web.dto.users.UsersUpdateRequestDto;
import bssm.ber.web.dto.users.UsersUpdateResponseDto;
import bssm.ber.web.generic.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UsersApiController {

    private final UsersService usersService;
    private final EmailService emailService;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public Long joinUser(@RequestBody UsersJoinRequestDto usersJoinRequestDto) throws Exception {
        return usersService.join(usersJoinRequestDto);
    }

    @PostMapping("/email/{address}")
    @ResponseBody
    public void emailConfirm(@PathVariable String address) throws Exception{
        log.info("코드 발송 완료!\n" + address + "에서 메일을 확인해주세요.");
        emailService.sendSimpleMessage(address);
    }

    @PostMapping("/checkCode/{code}")
    @ResponseBody
    public int verifyCode(@PathVariable String code) {
        log.info("verifyCode : " + code);

        int result;
        System.out.println("code match : " + EmailServiceImpl.ePw.equals(code));

        if (EmailServiceImpl.ePw.equals(code)) {
            result = 1; // 성공
        } else {
            result = 0; // 실패
            throw new IllegalArgumentException("코드를 다시 확인해주세요!");
        }
        return result;
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

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteUser(){
        return usersService.delete();
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
