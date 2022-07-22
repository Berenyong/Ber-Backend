package bssm.ber.service.users;

import bssm.ber.web.dto.users.*;

import java.util.List;
import java.util.Map;

public interface UsersService {

    Long join(UsersJoinRequestDto requestDto) throws Exception;

    UsersResponseDto findUser(Long id);

    List<UsersResponseDto> findUsers(String nickname);

    String delete(UserDeleteRequestDto request) throws Exception;

    String login(Map<String, String> users);

    UsersUpdateResponseDto update(UsersUpdateRequestDto usersUpdateRequestDto) throws Exception;
}
