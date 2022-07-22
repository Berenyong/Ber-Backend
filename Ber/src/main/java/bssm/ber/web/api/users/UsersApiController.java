package bssm.ber.web.api.users;

import bssm.ber.global.config.SecurityUtil;
import bssm.ber.service.email.EmailService;
import bssm.ber.service.email.impl.EmailServiceImpl;
import bssm.ber.service.users.UsersService;
import bssm.ber.web.dto.users.*;
import bssm.ber.web.dto.users.email.EmailRequestDto;
import bssm.ber.web.generic.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UsersApiController {

    private final UsersService usersService;
    private final EmailService emailService;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public Long joinUser(@RequestBody UsersJoinRequestDto usersJoinRequestDto) throws Exception {
        return usersService.join(usersJoinRequestDto);
    }

    @PostMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public void emailConfirm(@RequestBody EmailRequestDto requestDto) throws Exception{
        log.info("코드 발송 완료!\n" + requestDto.getEmail() + "에서 메일을 확인해주세요.");
        emailService.sendSimpleMessage(requestDto.getEmail());
    }

    @GetMapping("/finds/{nickname}")
    @ResponseStatus(HttpStatus.OK)
    public Result findUsers(@PathVariable String nickname){
        return new Result(usersService.findUsers(nickname));
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsersResponseDto findUser(@PathVariable Long id){
        return usersService.findUser(id);
    }

    @PostMapping("/email/delete")
    @ResponseStatus(HttpStatus.OK)
    public void confirmDeleteEmailSender() throws Exception{
        String email = SecurityUtil.getLoginUserEmail();
        log.info("코드 발송 완료!\n" + email + "에서 메일을 확인해주세요.");
        emailService.sendWithdrawalMessage(email);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@RequestBody UserDeleteRequestDto request) throws Exception {
        return usersService.delete(request);
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
