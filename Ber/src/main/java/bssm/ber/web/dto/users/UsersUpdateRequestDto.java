package bssm.ber.web.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class UsersUpdateRequestDto {

    private String email;
    private String nickname;
    private String password;
    private String gitLink;
    private String blogLink;

}