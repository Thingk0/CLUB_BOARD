package org.club_board.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.club_board.entity.Board;

@RequiredArgsConstructor
@Data
public class BoardResponseDto {

    private final String title;
    private final String content;
    private final String writer;

    public static BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(
                board.getTitle(),
                board.getContent(),
                board.getWriter());
    }
}
