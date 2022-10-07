package org.club_board.dto.board.request;

import lombok.*;
import org.club_board.entity.Board;
@Data
@Builder
@RequiredArgsConstructor
public class BoardCreateRequestDto {

    private final String title;
    private final String content;

    public static BoardCreateRequestDto toDto(Board board) {
        return BoardCreateRequestDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }

}
