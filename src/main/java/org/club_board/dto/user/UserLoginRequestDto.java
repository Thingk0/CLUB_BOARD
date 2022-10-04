package org.club_board.dto.user;

import lombok.Data;

@Data
public class UserLoginRequestDto {

    private String username;
    private String password;

}