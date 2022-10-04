package org.club_board.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.club_board.entity.Board;

@Data
@AllArgsConstructor
public class BoardResponseDto {

    private String title;
    private String content;
    private String writer;


    public static BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(
                board.getTitle(),
                board.getContent(),
                board.getWriter());
    }
}
