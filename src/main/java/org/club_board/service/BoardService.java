package org.club_board.service;

import lombok.RequiredArgsConstructor;
import org.club_board.dto.board.BoardCreateRequestDto;
import org.club_board.dto.board.BoardResponseDto;
import org.club_board.dto.board.BoardUpdateRequestDto;
import org.club_board.entity.Board;
import org.club_board.entity.User;
import org.club_board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // 전체 게시물 조회
    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoardList() {
        // 리포지터리로 부터 Board 객체를 모두 List 로 반환.
        List<Board> boards = boardRepository.findAll();

        // Dto 로 반환해주기 위해서 List 새롭게 생성.
        List<BoardResponseDto> boarList = new ArrayList<>();
        boards.forEach(board -> boarList.add(BoardResponseDto.toDto(board)));

        return boarList;
    }

    // 개별 게시물 조회
    @Transactional(readOnly = true)
    public BoardResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 게시글을 찾을 수 없습니다.");
        });

        return BoardResponseDto.toDto(board);
    }

    // 게시물 작성
    @Transactional
    public BoardResponseDto createBoard(BoardCreateRequestDto boardCreateRequestDto, User user) {
        Board board = Board.builder()
                .title(boardCreateRequestDto.getTitle())
                .content(boardCreateRequestDto.getContent())
                .writer(user.getNickname())
                .user(user)
                .build();

        boardRepository.save(board);
        return BoardResponseDto.toDto(board);
    }

    // 게시물 수정
    @Transactional
    public BoardUpdateRequestDto updateBoard(Long id, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 게시글을 찾을 수 없습니다!");
        });

        board.setTitle(boardUpdateRequestDto.getTitle());
        board.setContent(boardUpdateRequestDto.getContent());

        return BoardUpdateRequestDto.toDto(board);
    }


    // 게시글 삭제
    @Transactional
    public void deleteBoard(Long id) {
        // id 값으로, 게시글이 존재하는지 먼저 찾기.
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Board Id를 찾을 수 없습니다!");
        });

        // 게시글이 있는 경우 삭제
        boardRepository.deleteById(id);
    }

}
