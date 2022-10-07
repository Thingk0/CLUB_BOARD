package org.club_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ClubBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClubBoardApplication.class, args);
    }

}
