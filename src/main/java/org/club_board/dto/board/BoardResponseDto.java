package org.club_board.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.club_board.entity.Board;

@Data
public class BoardResponseDto {

    private String title;
    private String content;

    public BoardResponseDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(board.getTitle(), board.getContent());
    }
}
