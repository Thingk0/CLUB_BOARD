package org.club_board.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.club_board.entity.Board;
import org.club_board.entity.User;

@Data
@Builder
public class BoardCreateRequestDto {

    private String title;
    private String content;
    private String writer;

    public BoardCreateRequestDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
    public static BoardCreateRequestDto toDto(Board board) {
        return BoardCreateRequestDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .build();
    }


}
