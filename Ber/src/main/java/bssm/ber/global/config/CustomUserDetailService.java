package bssm.ber.global.config;

import bssm.ber.domain.users.UsersRepository;
import bssm.ber.global.exception.CustomException;
import bssm.ber.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}