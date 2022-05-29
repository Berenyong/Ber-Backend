package bssm.ber.service.users;

import bssm.ber.web.dto.users.UsersJoinRequestDto;
import bssm.ber.web.dto.users.UsersResponseDto;

import java.util.List;

public interface UsersService {

    Long join(UsersJoinRequestDto requestDto);

    UsersResponseDto findUser(Long id);

    List<UsersResponseDto> findUsers(String nickname);

    Long delete(Long id);
}
