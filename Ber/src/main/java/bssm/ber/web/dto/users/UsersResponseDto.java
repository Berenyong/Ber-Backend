package bssm.ber.web.dto.users;

import bssm.ber.domain.users.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UsersResponseDto {

    private Long id;
    private String nickname;
    private int age;
    private String role;

    @Builder
    public UsersResponseDto(Users users) {
        this.id = users.getId();
        this.nickname = users.getNickname();
        this.age = users.getAge();
        this.role = users.getRole().name();
    }

}
