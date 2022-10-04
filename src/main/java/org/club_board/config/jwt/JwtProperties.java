package org.club_board.config.jwt;

public interface JwtProperties {
    // 서버만 알고있는 시크릿키
    String SECRET = "mju_club_board_project";
    int EXPIRATION_TIME = 60000 * 60 ;  // 토근 유효 시간 1 hour
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";

}

