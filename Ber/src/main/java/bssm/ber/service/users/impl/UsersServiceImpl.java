package bssm.ber.service.users.impl;

import bssm.ber.domain.users.Users;
import bssm.ber.domain.users.UsersRepository;
import bssm.ber.security.jwt.JwtTokenProvider;
import bssm.ber.service.users.UsersService;
import bssm.ber.web.dto.users.UsersJoinRequestDto;
import bssm.ber.web.dto.users.UsersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public Long join(UsersJoinRequestDto requestDto) {
        return usersRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    public UsersResponseDto findUser(Long id) {
        Optional<Users> byId = Optional.ofNullable(usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다.")));
        Users users = byId.get();

        return new UsersResponseDto(users);
    }

    @Override
    public List<UsersResponseDto> findUsers(String nickname) {
        return usersRepository.findByNickname(nickname)
                .stream()
                .map(UsersResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Long delete(Long id) {
        Optional<Users> byId = Optional.ofNullable(usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다.")));
        usersRepository.deleteById(id);

        return byId.get().getId();
    }

    public String login(Map<String, String> users) {
        Optional<Users> user = Optional.ofNullable(usersRepository.findByEmail(users.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email입니다.")));
        Users findUser = user.get();
        if (!users.get("password").equals(findUser.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        return jwtTokenProvider.createToken(findUser.getUsername(), findUser.getRoles());
    }

}
