package org.club_board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;           // 이메일

    @JsonIgnore
    @Column(nullable = false)
    private String password;        // 비밀번호

    @JsonIgnore
    @Column(nullable = false)
    private String passwordOK;      // 비밀번호 확인

    @Column(nullable = false)
    private String nickname;        // 닉네임

}
