package bssm.ber.service.users.impl;

import bssm.ber.domain.users.Users;
import bssm.ber.domain.users.UsersRepository;
import bssm.ber.global.config.SecurityUtil;
import bssm.ber.global.exception.CustomException;
import bssm.ber.global.exception.ErrorCode;
import bssm.ber.global.jwt.JwtTokenProvider;
import bssm.ber.service.email.EmailService;
import bssm.ber.service.users.UsersService;
import bssm.ber.web.dto.users.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailService emailService;

    @Transactional
    @Override
    public Long join(UsersJoinRequestDto request) throws Exception {

        if (usersRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_EXISTS_USER);
        }
        if (!request.getPassword().equals(request.getCheckPassword())){
            throw new CustomException(ErrorCode.NOT_MATCH_PASSWORD);
        }

        if (!emailService.verifyCode(request.getCheckEmailCode())) {
            throw new CustomException(ErrorCode.NOT_MATCH_CODE);
        }

        Users user = usersRepository.save(request.toEntity());
        user.encodePassword(passwordEncoder);
        user.addUserAuthority();

        return user.getId();
    }

    @Override
    public UsersResponseDto findUser(Long id) {
        Optional<Users> byId = Optional.ofNullable(usersRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND)));
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
    public String delete(UserDeleteRequestDto request) throws Exception {

        if (SecurityUtil.getLoginUserEmail() == null) {
            throw new CustomException(ErrorCode.USER_NOT_LOGIN);
        }

        if (!emailService.verifyCode(request.getCheckEmailCode())) {
            throw new CustomException(ErrorCode.NOT_MATCH_CODE);
        }

        String myAccount = SecurityUtil.getLoginUserEmail();

        usersRepository.deleteByEmail(SecurityUtil.getLoginUserEmail());

        return myAccount;
    }

    public String login(Map<String, String> users) {
        Optional<Users> findUser = Optional.ofNullable(usersRepository.findByEmail(users.get("email"))
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_MATCH_ACCOUNT)));
        Users user = findUser.get();

        String password = users.get("password");
        if (!user.checkPassword(passwordEncoder, password)) {
            throw new CustomException(ErrorCode.NOT_MATCH_ACCOUNT);
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
