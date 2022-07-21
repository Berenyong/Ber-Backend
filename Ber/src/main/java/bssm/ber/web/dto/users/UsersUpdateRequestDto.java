package bssm.ber.web.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersUpdateRequestDto {

    private String email;
    private String nickname;
    private String password;
    private String gitLink;
    private String blogLink;

}