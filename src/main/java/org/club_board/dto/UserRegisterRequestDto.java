package org.club_board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.club_board.entity.User;

@Data
@NoArgsConstructor
public class UserRegisterRequestDto {
    private String email;
    private String password;
    private String passwordOK;
    private String nickname;

    public UserRegisterRequestDto(String email, String password, String passwordOK, String nickname) {
        this.email = email;
        this.password = password;
        this.passwordOK = passwordOK;
        this.nickname = nickname;
    }

    // Entity 로 변환.
    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .passwordOK(passwordOK)
                .nickname(nickname)
                .build();
    }

}
