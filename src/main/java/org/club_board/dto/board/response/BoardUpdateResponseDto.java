package org.club_board.dto.board.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.club_board.entity.Board;

import java.time.LocalDateTime;

@Data
@Builder
@RequiredArgsConstructor
public class BoardUpdateResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final String writer;
    private final LocalDateTime modified;

    public static BoardUpdateResponseDto toDto(Board board) {
        return BoardUpdateResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .modified(board.getModified())
                .build();
    }
}
