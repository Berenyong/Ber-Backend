package bssm.ber.web.dto.users;

import bssm.ber.domain.users.Users;
import lombok.Data;

@Data
public class UsersUpdateResponseDto {

    private String email;
    private String nickname;
    private String password;
    private String gitLink;
    private String blogLink;

    public UsersUpdateResponseDto(Users user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.password = user.getPassword();
        this.gitLink = user.getGitLink();
        this.blogLink = user.getBlogLink();
    }

}
