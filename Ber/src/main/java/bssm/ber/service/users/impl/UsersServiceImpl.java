package bssm.ber.service.users.impl;

import bssm.ber.domain.users.Users;
import bssm.ber.domain.users.UsersRepository;
import bssm.ber.security.SecurityUtil;
import bssm.ber.security.jwt.JwtTokenProvider;
import bssm.ber.service.users.UsersService;
import bssm.ber.web.dto.users.UsersUpdateRequestDto;
import bssm.ber.web.dto.users.UsersUpdateResponseDto;
import bssm.ber.web.dto.users.UsersJoinRequestDto;
import bssm.ber.web.dto.users.UsersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public Long join(UsersJoinRequestDto request) throws Exception {

        if (usersRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }
        if (!request.getPassword().equals(request.getCheckPassword())){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        Users user = usersRepository.save(request.toEntity());
        user.encodePassword(passwordEncoder);
        user.addUserAuthority();

        return user.getId();
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
        Optional<Users> findUser = Optional.ofNullable(usersRepository.findByEmail(users.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email입니다.")));
        Users user = findUser.get();

        String password = users.get("password");
        if (!user.checkPassword(passwordEncoder, password)) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        List<String> roles = new ArrayList<>();
        roles.add(user.getRole().name());

        return jwtTokenProvider.createToken(user.getUsername(), roles);
    }

    @Transactional
    @Override
    public UsersUpdateResponseDto update(UsersUpdateRequestDto request) throws Exception {
        Users user = usersRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new Exception("로그인이 필요합니다."));

        user.updateEmail(request.getEmail());
        user.updatePassword(passwordEncoder, request.getPassword());
        user.updateNickname(request.getNickname());
        user.updateGitLink(request.getGitLink());
        user.updateBlogLink(request.getBlogLink());

        return new UsersUpdateResponseDto(user);
    }

}
