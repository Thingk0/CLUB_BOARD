package org.club_board.service;

import lombok.RequiredArgsConstructor;
import org.club_board.dto.user.UserRegisterRequestDto;
import org.club_board.entity.User;
import org.club_board.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User register(UserRegisterRequestDto userRegisterRequestDto) {

        String encode = bCryptPasswordEncoder.encode(userRegisterRequestDto.getPassword());
        User user = userRegisterRequestDto.toEntity(encode);

        // 이메일을 통해 중복검사. 통과할 경우 return
//        validateDuplicateUserEmail(user);
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("해당하는 User ID를 찾을 수 없습니다.");
        });
    }

    // 아이디 중복검사
//    private void validateDuplicateUserEmail(User user) {
//        userRepository.findByEmail(user.getEmail())
//                .ifPresent(m -> {
//                    throw new IllegalStateException("이미 존재하는 이메일입니다.");
//                });
//    }
}


