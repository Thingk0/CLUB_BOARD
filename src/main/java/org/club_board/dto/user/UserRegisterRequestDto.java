package org.club_board.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.club_board.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDto {
    private String username;
    private String password;
    private String passwordOK;
    private String nickname;
    private String roles;

    // Entity 로 변환.
    public User toEntity(String passwordByEncode) {
        return User.builder()
                .username(username)
                .password(passwordByEncode)
                .passwordOK(passwordOK)
                .nickname(nickname)
                .roles("ROLE_USER")
                .build();
    }

}
