package bssm.ber.web.dto.users;

import bssm.ber.domain.users.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class UsersJoinRequestDto {

    private String email;
    private String nickname;
    private int age;
    private String password;
    private String checkPassword;

    public Users toEntity(){
        return Users.builder()
                .email(email)
                .nickname(nickname)
                .age(age)
                .password(password)
                .build();
    }
}
