package org.club_board.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;       // 게시글 제목

    @Column(nullable = false)
    private String content;     // 게시글 내용

    @Column(nullable = false)
    private String writer;      // 게시글 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 동아리 카테고리 id
    @Column(nullable = false)
    private Long clubId;
}


