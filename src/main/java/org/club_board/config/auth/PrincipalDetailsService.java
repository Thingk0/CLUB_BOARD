package org.club_board.config.auth;

import lombok.RequiredArgsConstructor;
import org.club_board.entity.User;
import org.club_board.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// http://localhost:8080/login => 여기서 동작을 안함 왜냐면 formLogin.disable() 해버려서
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userNickname) throws UsernameNotFoundException {
        User userEntity = userRepository.findByNickname(userNickname);
        return new PrincipalDetails(userEntity);
    }
}