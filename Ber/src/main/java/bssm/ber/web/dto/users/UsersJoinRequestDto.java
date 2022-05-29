package bssm.ber.web.dto.users;

import bssm.ber.domain.entity.users.Role;
import bssm.ber.domain.entity.users.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UsersJoinRequestDto {

    private String email;
    private String nickname;
    private int age;
    private String password;
    private Role role;

    public Users toEntity(){
        return Users.builder()
                .email(email)
                .nickname(nickname)
                .age(age)
                .password(password)
                .role(Role.USER)
                .build();
    }
}
