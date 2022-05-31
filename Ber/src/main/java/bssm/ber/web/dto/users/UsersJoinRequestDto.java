package bssm.ber.web.dto.users;

import bssm.ber.domain.entity.users.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class UsersJoinRequestDto {

    @NotNull(message = "이메일을 입력해주세요.")
//    @Pattern(regexp = "^[a-zA-Z\\d]+@[a-zA-Z\\d]+\\\\.[a-z]+$",
//             message = "이메일 형식을 지켜주세요.")
    @Email //이 어노테이션 안먹히면 정규식으로 바꾸면 됨
    private String email;
    @NotNull(message = "닉네임을 입력해주세요.")
    @Pattern(regexp = "^[가-힣\\da-zA-Z]+$",
            message = "알파벳, 한글, 숫자만 입력해주세요.")
    private String nickname;
    @NotNull(message = "나이를 입력해주세요.")
    @Pattern(regexp = "^\\d*$",
            message = "숫자만 입력해주세요.")
    private int age;
    @NotNull(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$\n",
            message = "숫자, 문자, 특수문 무조건 포함, 최소 8자에서 16자까지 입력해주세요.")
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
