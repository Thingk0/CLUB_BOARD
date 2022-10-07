package org.club_board.dto.board.request;

import lombok.*;
import org.club_board.entity.Board;

@Data
@Builder
@RequiredArgsConstructor
public class BoardUpdateRequestDto {

    private final String title;
    private final String content;

    public static BoardUpdateRequestDto toDto(Board board) {
        return BoardUpdateRequestDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }
}
