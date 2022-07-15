package bssm.ber.service.users;

import bssm.ber.web.dto.users.UsersUpdateRequestDto;
import bssm.ber.web.dto.users.UsersUpdateResponseDto;
import bssm.ber.web.dto.users.UsersJoinRequestDto;
import bssm.ber.web.dto.users.UsersResponseDto;

import java.util.List;
import java.util.Map;

public interface UsersService {

    Long join(UsersJoinRequestDto requestDto) throws Exception;

    UsersResponseDto findUser(Long id);

    List<UsersResponseDto> findUsers(String nickname);

    Long delete();

    String login(Map<String, String> users);

    UsersUpdateResponseDto update(UsersUpdateRequestDto usersUpdateRequestDto) throws Exception;
}
