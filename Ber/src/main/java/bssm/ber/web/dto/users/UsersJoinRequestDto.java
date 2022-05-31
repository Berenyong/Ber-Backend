package bssm.ber.web.dto.users;

import bssm.ber.domain.entity.users.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Pattern;
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

    private List<String> roles;

    public Users toEntity(){
        return Users.builder()
                .email(email)
                .nickname(nickname)
                .age(age)
                .password(password)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
    }
}
