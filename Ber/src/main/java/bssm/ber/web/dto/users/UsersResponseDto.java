package bssm.ber.web.dto.users;

import bssm.ber.domain.entity.users.Role;
import bssm.ber.domain.entity.users.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsersResponseDto {

    private Long id;
    private String email;
    private String nickname;
    private int age;
    private String password;
    private Role role;

    @Builder
    public UsersResponseDto(Users users) {
        this.id = users.getId();
        this.email = users.getEmail();
        this.nickname = users.getNickname();
        this.age = users.getAge();
        this.password = users.getPassword();
        this.role = users.getRole();
    }

}
