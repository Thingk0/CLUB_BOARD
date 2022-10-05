package org.club_board.dto.board;

import lombok.*;
import org.club_board.entity.Board;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardCreateRequestDto {

    private String title;
    private String content;

    public BoardCreateRequestDto toDto(Board board) {
        return BoardCreateRequestDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }
}
