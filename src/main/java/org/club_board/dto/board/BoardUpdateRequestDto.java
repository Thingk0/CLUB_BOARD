package org.club_board.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.club_board.entity.Board;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateRequestDto {

    private String title;
    private String content;
    public static BoardUpdateRequestDto toDto(Board board) {
        return BoardUpdateRequestDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }
}
